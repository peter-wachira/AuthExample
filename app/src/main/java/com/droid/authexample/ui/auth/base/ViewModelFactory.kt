package com.droid.authexample.ui.auth.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droid.authexample.repository.AuthRepository
import com.droid.authexample.repository.BaseRepository
import com.droid.authexample.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private  val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository)  as T

           else ->throw  IllegalArgumentException("View model class not found")
        }
    }

}