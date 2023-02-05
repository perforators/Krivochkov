package com.krivochkov.krivochkov.presentation.filmsbycategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krivochkov.krivochkov.domain.usecases.AddFilmToFavouritesUseCase
import com.krivochkov.krivochkov.domain.usecases.FetchFilmsUseCase
import com.krivochkov.krivochkov.presentation.SearchQueryFilter

class FilmsByCategoryViewModelFactory(
    private val fetchFilmsUseCase: FetchFilmsUseCase,
    private val addFilmToFavouritesUseCase: AddFilmToFavouritesUseCase,
    private val searchQueryFilter: SearchQueryFilter
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmsByCategoryViewModel(
            fetchFilmsUseCase,
            addFilmToFavouritesUseCase,
            searchQueryFilter
        ) as T
    }
}