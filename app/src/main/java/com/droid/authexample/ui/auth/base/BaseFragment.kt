package com.droid.authexample.ui.auth.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.droid.authexample.data.UserPreferences
import com.droid.authexample.data.network.RemoteDataSource
import com.droid.authexample.data.repository.BaseRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM: ViewModel, B: ViewBinding,R: BaseRepository> : Fragment(){
    protected  lateinit var  binding: B
    protected val remoteDataSource = RemoteDataSource()
    protected  lateinit var  viewModel: VM
    protected  lateinit var userPreferences: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater,container)
        userPreferences = UserPreferences(requireContext())
        val factory = ViewModelFactory(geFragmentrepository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel())

        lifecycleScope.launch {
            userPreferences.authToken.first()
        }

        return binding.root
    }

    abstract  fun getViewModel() : Class<VM>

    abstract  fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?) :B

    abstract  fun geFragmentrepository(): R


}