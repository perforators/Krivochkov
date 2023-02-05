package com.krivochkov.krivochkov.domain.usecases

import com.krivochkov.krivochkov.domain.FilmsRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddFilmToFavouritesUseCase @Inject constructor(
    private val repository: FilmsRepository
) {
    fun add(filmId: Int): Completable = repository.addFilmToFavourites(filmId)
}