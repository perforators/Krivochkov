package com.krivochkov.krivochkov.data

import com.krivochkov.krivochkov.data.mappers.mapToFilm
import com.krivochkov.krivochkov.data.remote.api.FilmsApi
import com.krivochkov.krivochkov.data.local.cache.InMemoryCache
import com.krivochkov.krivochkov.data.local.database.dao.FavouriteFilmsDao
import com.krivochkov.krivochkov.data.mappers.mapToEntity
import com.krivochkov.krivochkov.domain.FilmsRepository
import com.krivochkov.krivochkov.domain.model.Film
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val api: FilmsApi,
    private val cache: InMemoryCache<Int, Film>,
    private val dao: FavouriteFilmsDao
) : FilmsRepository {

    override fun fetchAllFilms(): Single<List<Film>> {
        if (cache.size() == FULL_COUNT_CACHED_FILMS) return Single.just(cache.getAllValues())
        return Observable.fromIterable(INITIAL_PAGE_NUM..LAST_PAGE_NUM)
            .concatMap { pageNum ->
                api.fetchPopularFilms(pageNum)
                    .flatMapObservable { Observable.fromIterable(it.films) }
                    .retry(RETRY_COUNT)
            }
            .flatMapSingle { filmDto ->
                dao.fetchFilmById(filmDto.filmId)
                    .map {
                        val isFavourite = it.isNotEmpty()
                        val description = it.firstOrNull()?.description ?: ""
                        val film = filmDto.mapToFilm(description, isFavourite)
                        cache.put(filmDto.filmId, film)
                        film
                    }
                    .subscribeOn(Schedulers.io())
            }
            .toList()
    }

    override fun fetchFavouriteFilms(): Single<List<Film>> {
        return dao.fetchAllFilms().map { it.map { entity -> entity.mapToFilm() } }
    }

    override fun fetchFilm(filmId: Int): Single<Film> {
        TODO("Not yet implemented")
    }

    override fun addFilmToFavourites(filmId: Int): Completable = Single.just(cache[filmId]!!)
        .flatMap { film ->
            if (film.description.isEmpty()) {
                fetchFilmDescription(filmId).map { film.copy(description = it) }
            } else {
                Single.just(film)
            }
        }
        .map {
            cache.put(filmId, it.copy(isFavourite = true))
            dao.insertFilm(it.mapToEntity())
        }
        .ignoreElement()

    private fun fetchFilmDescription(filmId: Int): Single<String> {
        return api.fetchFilmDescription(filmId).map { it.description }
    }

    companion object {
        private const val INITIAL_PAGE_NUM = 1
        private const val LAST_PAGE_NUM = 5
        private const val FULL_COUNT_CACHED_FILMS = 100
        private const val RETRY_COUNT = 5L
    }
}