package com.krivochkov.krivochkov.di.films

import com.krivochkov.krivochkov.di.films.modules.DomainModule
import com.krivochkov.krivochkov.di.films.modules.PresentationModule
import com.krivochkov.krivochkov.presentation.filmsbycategory.favouritefilms.FavouriteFilmsFragment
import com.krivochkov.krivochkov.presentation.filmsbycategory.topfilms.TopFilmsFragment
import dagger.Component

@Component(
    modules = [
        DomainModule::class,
        PresentationModule::class
    ],
    dependencies = [FilmsScreenDependencies::class]
)
interface FilmsScreenComponent {

    fun inject(fragment: TopFilmsFragment)

    fun inject(fragment: FavouriteFilmsFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: FilmsScreenDependencies): FilmsScreenComponent
    }
}