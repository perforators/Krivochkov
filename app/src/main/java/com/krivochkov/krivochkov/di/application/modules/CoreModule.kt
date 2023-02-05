package com.krivochkov.krivochkov.di.application.modules

import com.krivochkov.krivochkov.presentation.SearchQueryFilter
import dagger.Module
import dagger.Provides

@Module
class CoreModule {

    @Provides
    fun provideSearchQueryFilter() = SearchQueryFilter()
}