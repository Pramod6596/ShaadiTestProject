package com.people_interactive.test.repositories

import com.people_interactive.test.models.UserResponse
import com.people_interactive.test.repositories.api.ApiClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository
{
    public fun getUsers(onResult: (isSuccess: Boolean, errorResponse: String?, response: UserResponse?,
                                                    exception: Throwable?) -> Unit) {
        ApiClient.instance.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>) =
                if (response.isSuccessful) {
                    onResult(true, null, response.body(),null)
                }
                else {
                    onResult(false, response.errorBody()?.string(), null,null)
                }

            override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                onResult(false, null, null,t)
            }
        })
    }

    companion object {
        private var INSTANCE: UserRepository? = null
        fun getInstance() = INSTANCE
            ?: UserRepository().also {
                INSTANCE = it
            }
    }
}