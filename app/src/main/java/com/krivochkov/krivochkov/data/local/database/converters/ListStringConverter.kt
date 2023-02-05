package com.krivochkov.krivochkov.data.local.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListStringConverter {
    @TypeConverter
    fun convertListToString(list: List<String>) = Json.encodeToString(list)

    @TypeConverter
    fun convertStringToList(string: String) = Json.decodeFromString<List<String>>(string)
}