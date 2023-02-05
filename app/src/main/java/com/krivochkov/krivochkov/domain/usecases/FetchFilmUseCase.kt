package com.krivochkov.krivochkov.domain.usecases

import com.krivochkov.krivochkov.domain.FilmsRepository
import javax.inject.Inject

class FetchFilmUseCase @Inject constructor(
    private val repository: FilmsRepository
) {
    fun fetch(filmId: Int) = repository.fetchFilm(filmId)
}