package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.RegisteredModel

class RegisteredConverter
{
    @TypeConverter
    fun fromRegistered(value: String?): RegisteredModel? {
        val listType = object : TypeToken<RegisteredModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toRegistered(list: RegisteredModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}