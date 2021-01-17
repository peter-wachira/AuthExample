package com.droid.authexample.data.repository
import com.droid.authexample.data.UserPreferences
import com.droid.authexample.data.network.AuthApi
import com.droid.authexample.data.network.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository(){

    suspend fun getUser() =safeApiCall {
        api.getUser()
    }
    suspend fun  logout() = safeApiCall {
        api.logout()
    }
 }