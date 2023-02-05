package com.krivochkov.krivochkov.data.remote.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GenreDto(
    @SerialName("genre") val genre: String
)