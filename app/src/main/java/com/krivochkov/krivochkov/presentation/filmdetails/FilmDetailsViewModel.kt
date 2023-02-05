package com.krivochkov.krivochkov.presentation.filmdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krivochkov.krivochkov.domain.usecases.FetchFilmUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FilmDetailsViewModel(
    private val fetchFilmUseCase: FetchFilmUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ScreenState>(ScreenState.Init)
    val state: LiveData<ScreenState>
        get() = _state

    fun fetchFilmInfo(filmId: Int) {
        _state.value = ScreenState.Loading
        fetchFilmUseCase.fetch(filmId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { _state.value = ScreenState.Failure },
                onSuccess = { _state.value = ScreenState.FilmSucceedLoaded(it) }
            )
    }
}