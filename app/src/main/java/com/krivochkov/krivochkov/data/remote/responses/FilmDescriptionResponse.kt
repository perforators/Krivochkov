package com.krivochkov.krivochkov.data.remote.responses

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FilmDescriptionResponse(
    @SerialName("kinopoiskId") val filmId: Int,
    @SerialName("description") val description: String
)