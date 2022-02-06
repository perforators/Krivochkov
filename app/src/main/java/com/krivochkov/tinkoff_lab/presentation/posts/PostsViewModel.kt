package com.krivochkov.tinkoff_lab.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krivochkov.tinkoff_lab.domain.use_cases.GetPostUseCase
import com.krivochkov.tinkoff_lab.presentation.ScreenState
import com.krivochkov.tinkoff_lab.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor (
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private var postsCount = 0
    private var currentPostNumber = 1

    private val _state = MutableStateFlow<ScreenState>(ScreenState.Init)
    val state = _state.asStateFlow()

    fun currentPost(category: String) {
        getPost(category, currentPostNumber){}
    }

    fun nextPost(category: String) {
        getPost(category, currentPostNumber + 1) {
            currentPostNumber++
            postsCount++
        }
    }

    fun prevPost(category: String) {
        getPost(category, currentPostNumber - 1) {
            currentPostNumber--
        }
    }

    private fun getPost(category: String, numPost: Int, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            getPostUseCase(category, numPost).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        onSuccess()
                        val state = if (currentPostNumber == 1) {
                            ScreenState.FirstPostLoaded(result.data!!)
                        } else {
                            ScreenState.NonFirstPostLoaded(result.data!!)
                        }
                        _state.value = state
                    }

                    is Resource.Error -> {
                        _state.value = ScreenState.Failure
                    }

                    is Resource.Loading -> {
                        _state.value = ScreenState.Loading
                    }
                }
            }
        }
    }
}