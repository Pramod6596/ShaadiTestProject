package com.people_interactive.test.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.people_interactive.test.models.*

@Entity
data class TUser(

    @PrimaryKey(autoGenerate = true)
    var userId : Long = 0,

    var gender : String,
    val name : NameModel,
    val location : LocationModel,
    val email : String,
    val login : LoginModel,
    val dob : DateModel,
    val registered : RegisteredModel,
    val phone : String,
    val cell : String,
    val id : IdModel,
    val picture : PictureModel,
    val nat : String,

    //1-Accept, 2-Deny
    var status : Int = 0
)