package com.krivochkov.krivochkov.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.krivochkov.krivochkov.data.local.database.entity.FilmEntity
import com.krivochkov.krivochkov.domain.model.Film
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavouriteFilmsDao {

    @Query("SELECT * FROM favourite_films")
    fun fetchAllFilms(): Single<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: FilmEntity)

    @Query("SELECT * FROM favourite_films WHERE id = :filmId")
    fun fetchFilmById(filmId: Int): Single<List<FilmEntity>>
}