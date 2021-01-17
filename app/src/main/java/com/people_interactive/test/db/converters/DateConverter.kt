package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.DateModel

class DateConverter
{
    @TypeConverter
    fun fromDate(value: String?): DateModel? {
        val listType = object : TypeToken<DateModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toDate(list: DateModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}