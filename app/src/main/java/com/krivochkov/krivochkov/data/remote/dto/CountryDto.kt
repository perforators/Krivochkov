package com.krivochkov.krivochkov.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("country") val country: String
)