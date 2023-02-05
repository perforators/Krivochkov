package com.krivochkov.krivochkov.di.films

import com.krivochkov.krivochkov.domain.FilmsRepository
import com.krivochkov.krivochkov.presentation.SearchQueryFilter

interface FilmsScreenDependencies {

    fun getFilmsRepository(): FilmsRepository

    fun getSearchQueryFilter(): SearchQueryFilter
}