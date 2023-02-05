package com.krivochkov.krivochkov.presentation.filmsbycategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krivochkov.krivochkov.presentation.SingleEvent

class FilmsSharedViewModel : ViewModel() {

    private val _searchQuery: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    val searchQuery: LiveData<SingleEvent<String>>
        get() = _searchQuery

    private val _showFilmInfoEvent: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val showFilmInfoEvent: LiveData<SingleEvent<Int>>
        get() = _showFilmInfoEvent

    fun sendSearchQuery(query: String) {
        _searchQuery.value = SingleEvent(query)
    }

    fun showFilmInfo(filmId: Int) {
        _showFilmInfoEvent.value = SingleEvent(filmId)
    }
}