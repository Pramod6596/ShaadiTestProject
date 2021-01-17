package com.people_interactive.test.models

import com.people_interactive.test.db.entities.TUser

data class UserResponse(
    val results : List<TUser>
)
