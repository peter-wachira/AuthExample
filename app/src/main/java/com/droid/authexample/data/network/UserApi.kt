package com.droid.authexample.data.network

import com.droid.authexample.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {
    @GET("user")
    suspend fun getUser(): LoginResponse
}