package com.droid.authexample.ui.auth.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droid.authexample.data.network.Resource
import com.droid.authexample.data.repository.UserRepository
import com.droid.authexample.data.responses.LoginResponse
import com.droid.authexample.ui.auth.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private  val repository: UserRepository
): BaseViewModel(repository){
    private val _user:MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user :LiveData<Resource<LoginResponse>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}