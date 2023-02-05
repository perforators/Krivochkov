package com.krivochkov.krivochkov.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.krivochkov.krivochkov.data.local.database.converters.ListStringConverter
import com.krivochkov.krivochkov.data.local.database.dao.FavouriteFilmsDao
import com.krivochkov.krivochkov.data.local.database.entity.FilmEntity

@Database(
    entities = [FilmEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class FavouriteFilmsDatabase : RoomDatabase() {
    abstract fun favouriteFilmsDao(): FavouriteFilmsDao
}