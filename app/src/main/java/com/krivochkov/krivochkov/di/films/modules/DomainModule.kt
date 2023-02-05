package com.krivochkov.krivochkov.di.films.modules

import com.krivochkov.krivochkov.di.films.annotations.FavouriteFilms
import com.krivochkov.krivochkov.di.films.annotations.TopFilms
import com.krivochkov.krivochkov.domain.usecases.FetchTopFilmsUseCase
import com.krivochkov.krivochkov.domain.usecases.FetchFavouriteFilmsUseCase
import com.krivochkov.krivochkov.domain.usecases.FetchFilmsUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @FavouriteFilms
    fun bindFetchFavouriteFilmsUseCase(impl: FetchFavouriteFilmsUseCase): FetchFilmsUseCase

    @Binds
    @TopFilms
    fun bindFetchTopFilmsUseCase(impl: FetchTopFilmsUseCase): FetchFilmsUseCase
}