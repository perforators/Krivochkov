package com.krivochkov.krivochkov.domain.model

data class Film(
    val id: Int,
    val name: String,
    val countries: List<String>,
    val genres: List<String>,
    val year: String,
    val posterUrl: String,
    val description: String,
    val isFavourite: Boolean
)