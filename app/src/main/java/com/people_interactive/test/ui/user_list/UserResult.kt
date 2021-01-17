package com.people_interactive.test.ui.user_list

import com.people_interactive.test.db.entities.TUser

data class UserResult(
    val results: List<TUser>? = null,
    val errorResponse: String? = null,
    val exception: Throwable?=null
)