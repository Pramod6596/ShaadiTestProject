package com.people_interactive.test.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.people_interactive.test.models.LoginModel

class LoginConverter
{
    @TypeConverter
    fun fromLogin(value: String?): LoginModel? {
        val listType = object : TypeToken<LoginModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toLogin(list: LoginModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}