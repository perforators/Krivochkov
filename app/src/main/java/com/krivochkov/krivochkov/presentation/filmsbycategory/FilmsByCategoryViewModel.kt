package com.krivochkov.krivochkov.presentation.filmsbycategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krivochkov.krivochkov.domain.usecases.AddFilmToFavouritesUseCase
import com.krivochkov.krivochkov.domain.usecases.FetchFilmsUseCase
import com.krivochkov.krivochkov.presentation.SearchQueryFilter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FilmsByCategoryViewModel(
    private val fetchFilmsUseCase: FetchFilmsUseCase,
    private val addFilmToFavouritesUseCase: AddFilmToFavouritesUseCase,
    private val searchQueryFilter: SearchQueryFilter
) : ViewModel() {

    private val _state = MutableLiveData<ScreenState>(ScreenState.Init)
    val state: LiveData<ScreenState>
        get() = _state

    private var previousQuery: String = ""

    init {
        fetchFilms()
        searchQueryFilter.getFilterQueriesObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { query ->
                previousQuery = query
                fetchFilms(query)
            }
    }

    fun fetchFilms(searchQuery: String = "") {
        _state.value = ScreenState.Loading
        fetchFilmsUseCase.fetch { it.name.contains(searchQuery) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { _state.value = ScreenState.Failure },
                onSuccess = { _state.value = ScreenState.FilmsSucceedLoaded(it) }
            )
    }

    fun repeatPreviousQuery() {
        fetchFilms(previousQuery)
    }

    fun addFilmToFavourites(filmId: Int) {
        addFilmToFavouritesUseCase.add(filmId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {  },
                onComplete = { fetchFilms() }
            )
    }

    fun searchFilms(searchQuery: String) {
        searchQueryFilter.sendQuery(searchQuery)
    }

    override fun onCleared() {
        super.onCleared()
        searchQueryFilter.dispose()
    }
}