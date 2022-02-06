package com.krivochkov.tinkoff_lab.presentation

import com.krivochkov.tinkoff_lab.domain.model.Post

sealed class ScreenState {
    data class NonFirstPostLoaded(val result: Post): ScreenState()
    data class FirstPostLoaded(val result: Post): ScreenState()
    object Failure : ScreenState()
    object Loading : ScreenState()
    object Init: ScreenState()
}
