package com.people_interactive.test

import android.app.Application
import android.content.Context

class AppController : Application()
{
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit  var appContext: Context
    }
}