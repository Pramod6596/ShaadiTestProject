package com.people_interactive.test.util

import android.content.Context
import android.content.SharedPreferences
import com.people_interactive.test.AppController

object SharedPreferenceManager {

    private const val sharedPrefFile = "paras_aerospace"
    private const val LOGGED_IN: String = "loggedIn"

    private val sharedPreferences: SharedPreferences =
        AppController.appContext.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

    private fun setBoolean(key: String, value: Boolean): Unit {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
        editor.commit()
    }

    private fun setString(key: String, value: String): Unit {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    private fun setInt(key: String, value: Int): Unit {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
        editor.commit()
    }

    private fun getBoolValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun getStringValue(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    private fun getIntValue(key: String): Int? {
        return sharedPreferences.getInt(key, 0)
    }

    fun getLoggedIn(): Boolean {
        return getBoolValue(LOGGED_IN)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        setBoolean(LOGGED_IN, isLoggedIn)
    }

    fun clear()
    {
        sharedPreferences.edit().clear().commit()
    }

}