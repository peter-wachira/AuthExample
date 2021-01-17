package com.droid.authexample.data.repository
import com.droid.authexample.data.UserPreferences
import com.droid.authexample.data.network.AuthApi
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){
    suspend fun login(
        email: String,
        password : String
    ) = safeApiCall {
        api.login(email,password)
    }

    suspend fun  saveAuthToken( token: String){
      preferences.saveAuthToken(token)
    }
 }