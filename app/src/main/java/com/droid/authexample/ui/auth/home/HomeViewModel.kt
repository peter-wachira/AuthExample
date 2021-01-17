package com.droid.authexample.ui.auth.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droid.authexample.data.network.Resource
import com.droid.authexample.data.network.UserApi
import com.droid.authexample.data.repository.UserRepository
import com.droid.authexample.data.responses.LoginResponse
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private  val repository: UserRepository
): ViewModel(){
    private val _user:MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user :LiveData<Resource<LoginResponse>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
    suspend fun  logout()= repository.logout()

}