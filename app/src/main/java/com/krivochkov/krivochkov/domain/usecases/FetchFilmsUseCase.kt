package com.krivochkov.krivochkov.domain.usecases

import com.krivochkov.krivochkov.domain.model.Film
import io.reactivex.Single

interface FetchFilmsUseCase {
    fun fetch(filter: (Film) -> Boolean = { true }): Single<List<Film>>
}