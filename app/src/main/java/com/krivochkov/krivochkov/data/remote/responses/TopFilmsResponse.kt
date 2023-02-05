package com.krivochkov.krivochkov.data.remote.responses

import com.krivochkov.krivochkov.data.remote.dto.FilmDto
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TopFilmsResponse(
    @SerialName("films") val films: List<FilmDto>,
    @SerialName("pagesCount") val pagesCount: Int
)