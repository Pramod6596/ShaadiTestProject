package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.IdModel

class IdConverter
{
    @TypeConverter
    fun fromId(value: String?): IdModel? {
        val listType = object : TypeToken<IdModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toId(list: IdModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}