package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.LocationModel

class LocationConverter {
    @TypeConverter
    fun fromLocation(value: String?): LocationModel? {
        val listType = object : TypeToken<LocationModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toLocation(list: LocationModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}