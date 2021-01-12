package com.droid.authexample.ui.auth.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droid.authexample.data.repository.AuthRepository
import com.droid.authexample.data.repository.BaseRepository
import com.droid.authexample.data.repository.UserRepository
import com.droid.authexample.ui.auth.auth.AuthViewModel
import com.droid.authexample.ui.auth.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private  val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository)  as T
           modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T

           else ->throw  IllegalArgumentException("View model class not found")
        }
    }

}