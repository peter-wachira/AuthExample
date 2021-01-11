package com.droid.authexample.data.network

import com.droid.authexample.data.responses.LoginResponse
import com.droid.authexample.ui.auth.LoginFragment
import retrofit2.http.GET

interface UserApi {
    @GET("user")
    suspend fun getUser(): LoginResponse
}