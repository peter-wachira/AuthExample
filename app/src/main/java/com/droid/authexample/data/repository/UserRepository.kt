package com.droid.authexample.data.repository
import com.droid.authexample.data.UserPreferences
import com.droid.authexample.data.network.AuthApi
import com.droid.authexample.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository(){

    suspend fun getUser() =safeApiCall {
        api.getUser()
    }
 }