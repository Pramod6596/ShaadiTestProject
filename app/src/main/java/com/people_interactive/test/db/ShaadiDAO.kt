package com.people_interactive.test.db

import androidx.room.*
import com.people_interactive.test.db.entities.TUser

@Dao
interface ShaadiDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(corridor: List<TUser>)

    @Query("Select * from TUser")
    fun getUsers() : List<TUser>

    @Update
    fun updateUser(user : TUser) :Int

}