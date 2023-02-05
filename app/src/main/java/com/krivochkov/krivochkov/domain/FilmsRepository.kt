package com.krivochkov.krivochkov.domain

import com.krivochkov.krivochkov.domain.model.Film
import io.reactivex.Completable
import io.reactivex.Single

interface FilmsRepository {
    fun fetchAllFilms(): Single<List<Film>>
    fun fetchFavouriteFilms(): Single<List<Film>>
    fun fetchFilm(filmId: Int): Single<Film>
    fun addFilmToFavourites(filmId: Int): Completable
}