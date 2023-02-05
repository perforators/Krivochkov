package com.krivochkov.krivochkov.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_films")
data class FilmEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val countries: List<String>,
    val genres: List<String>,
    val year: String,
    val posterUrl: String,
    val description: String,
)