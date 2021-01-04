package com.droid.authexample.repository

import com.droid.authexample.network.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository(){
    suspend fun login(
        email: String,
        password : String
    ) = safeApiCall {
        api.login(email,password)
    }
 }