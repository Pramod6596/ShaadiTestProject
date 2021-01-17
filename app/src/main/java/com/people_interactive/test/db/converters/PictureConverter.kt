package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.PictureModel

class PictureConverter
{
    @TypeConverter
    fun fromPicture(value: String?): PictureModel? {
        val listType = object : TypeToken<PictureModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toPicture(list: PictureModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}