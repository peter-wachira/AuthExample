package com.droid.authexample.ui.auth.base

import androidx.lifecycle.ViewModel
import com.droid.authexample.data.network.UserApi
import com.droid.authexample.data.repository.BaseRepository

abstract class BaseViewModel(
    private  val repository: BaseRepository
) : ViewModel() {
    suspend fun  logout(api:UserApi)= repository.logout(api)
}