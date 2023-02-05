package com.krivochkov.krivochkov.domain.usecases

import com.krivochkov.krivochkov.domain.FilmsRepository
import com.krivochkov.krivochkov.domain.model.Film
import io.reactivex.Single
import javax.inject.Inject

class FetchFavouriteFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) : FetchFilmsUseCase {
    override fun fetch(filter: (Film) -> Boolean): Single<List<Film>> = repository
        .fetchFavouriteFilms()
        .map { films -> films.filter(filter).sortedBy { it.id } }
}