package com.krivochkov.krivochkov.presentation.filmdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krivochkov.krivochkov.domain.usecases.FetchFilmUseCase
import javax.inject.Inject

class FilmDetailsViewModelFactory @Inject constructor(
    private val fetchFilmUseCase: FetchFilmUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmDetailsViewModel(fetchFilmUseCase) as T
    }
}