package com.krivochkov.krivochkov.di.application.modules

import android.content.Context
import androidx.room.Room
import com.krivochkov.krivochkov.data.local.cache.FilmsCache
import com.krivochkov.krivochkov.data.local.cache.InMemoryCache
import com.krivochkov.krivochkov.data.local.database.FavouriteFilmsDatabase
import com.krivochkov.krivochkov.domain.model.Film
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideFavouriteFilmsDatabase(context: Context) = Room.databaseBuilder(
        context,
        FavouriteFilmsDatabase::class.java,
        DB_NAME
    ).build()

    @Provides
    fun provideFavouritesFilmsDao(database: FavouriteFilmsDatabase) = database.favouriteFilmsDao()

    @Singleton
    @Provides
    fun provideInMemoryCache(): InMemoryCache<Int, Film> = FilmsCache()

    companion object {
        private const val DB_NAME = "favourite_films_database"
    }
}