package com.example.reusabelpostfeed.roomdb

import androidx.room.TypeConverter
import org.json.JSONArray

class Converter {
    @TypeConverter
    fun fromList(list: List<String>): String = JSONArray(list).toString()
    @TypeConverter
    fun toList(json: String): List<String> {
        val arr = JSONArray(json)
        return List(arr.length()) { idx -> arr.optString(idx) }
    }
}