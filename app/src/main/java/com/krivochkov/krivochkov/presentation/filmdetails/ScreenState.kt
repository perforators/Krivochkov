package com.krivochkov.krivochkov.presentation.filmdetails

import com.krivochkov.krivochkov.domain.model.Film

sealed interface ScreenState {
    data class FilmSucceedLoaded(val film: Film) : ScreenState
    object Failure : ScreenState
    object Loading : ScreenState
    object Init : ScreenState
}