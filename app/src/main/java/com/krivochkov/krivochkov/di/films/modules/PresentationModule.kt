package com.krivochkov.krivochkov.di.films.modules

import com.krivochkov.krivochkov.di.films.annotations.FavouriteFilms
import com.krivochkov.krivochkov.di.films.annotations.TopFilms
import com.krivochkov.krivochkov.domain.usecases.AddFilmToFavouritesUseCase
import com.krivochkov.krivochkov.domain.usecases.FetchFilmsUseCase
import com.krivochkov.krivochkov.presentation.SearchQueryFilter
import com.krivochkov.krivochkov.presentation.filmsbycategory.FilmsByCategoryViewModelFactory
import com.krivochkov.krivochkov.presentation.filmsbycategory.adapters.FilmsAdapter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideFilmsAdapter() = FilmsAdapter()

    @Provides
    @FavouriteFilms
    fun provideFavouriteFilmsViewModelFactory(
        @FavouriteFilms fetchFilmsUseCase: FetchFilmsUseCase,
        addFilmToFavouritesUseCase: AddFilmToFavouritesUseCase,
        searchQueryFilter: SearchQueryFilter
    ) = FilmsByCategoryViewModelFactory(
        fetchFilmsUseCase,
        addFilmToFavouritesUseCase,
        searchQueryFilter
    )

    @Provides
    @TopFilms
    fun provideTopFilmsViewModelFactory(
        @TopFilms fetchFilmsUseCase: FetchFilmsUseCase,
        addFilmToFavouritesUseCase: AddFilmToFavouritesUseCase,
        searchQueryFilter: SearchQueryFilter
    ) = FilmsByCategoryViewModelFactory(
        fetchFilmsUseCase,
        addFilmToFavouritesUseCase,
        searchQueryFilter
    )
}