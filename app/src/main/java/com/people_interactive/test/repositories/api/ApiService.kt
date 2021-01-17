package com.people_interactive.test.repositories.api

import com.people_interactive.test.db.entities.TUser
import com.people_interactive.test.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(APIConstants.BASE_URL)
    fun getUsers(): Call<UserResponse>
}