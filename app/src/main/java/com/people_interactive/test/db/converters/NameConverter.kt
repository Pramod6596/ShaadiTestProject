package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.NameModel

class NameConverter
{
    @TypeConverter
    fun fromName(value: String?): NameModel? {
        val listType = object : TypeToken<NameModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toName(list: NameModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}