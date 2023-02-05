package com.krivochkov.krivochkov.data.remote.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FilmDto(
    @SerialName("filmId") val filmId: Int,
    @SerialName("countries") val countries: List<CountryDto>,
    @SerialName("genres") val genres: List<GenreDto>,
    @SerialName("nameRu") val nameRu: String,
    @SerialName("posterUrl") val posterUrl: String,
    @SerialName("year") val year: String
)