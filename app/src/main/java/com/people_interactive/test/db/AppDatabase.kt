package com.people_interactive.test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.people_interactive.test.db.converters.*
import com.people_interactive.test.db.entities.TUser

@Database(entities = [TUser::class], version = 1, exportSchema = false)

@TypeConverters(NameConverter::class, LocationConverter::class, LoginConverter::class, DateConverter::class, RegisteredConverter::class,
                    IdConverter::class, PictureConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun shaadiDAO(): ShaadiDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "shaadi.db")
            .allowMainThreadQueries()
            .build()
    }
}