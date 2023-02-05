package com.krivochkov.krivochkov.presentation.filmsbycategory

import com.krivochkov.krivochkov.domain.model.Film

sealed interface ScreenState {
    data class FilmsSucceedLoaded(val films: List<Film>) : ScreenState
    object Failure : ScreenState
    object Loading : ScreenState
    object Init : ScreenState
}