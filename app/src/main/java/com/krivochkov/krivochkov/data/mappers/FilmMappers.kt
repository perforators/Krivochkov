package com.krivochkov.krivochkov.data.mappers

import com.krivochkov.krivochkov.data.local.database.entity.FilmEntity
import com.krivochkov.krivochkov.data.remote.dto.FilmDto
import com.krivochkov.krivochkov.domain.model.Film

fun FilmDto.mapToFilm(description: String, isFavourite: Boolean): Film = Film(
    id = filmId,
    name = nameRu,
    countries = countries.map { it.country },
    genres = genres.map { it.genre },
    year = year,
    posterUrl = posterUrl,
    description = description,
    isFavourite = isFavourite
)

fun FilmEntity.mapToFilm() = Film(
    id = id,
    name = name,
    countries = countries,
    genres = genres,
    year = year,
    posterUrl = posterUrl,
    description = description,
    isFavourite = true
)

fun Film.mapToEntity() = FilmEntity(
    id = id,
    name = name,
    countries = countries,
    genres = genres,
    year = year,
    posterUrl = posterUrl,
    description = description
)