package com.droid.authexample.ui.auth.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.droid.authexample.network.RemoteDataSource
import com.droid.authexample.repository.BaseRepository

abstract class BaseFragment<VM: ViewModel, B: ViewBinding,R: BaseRepository> : Fragment(){
    protected  lateinit var  binding: B
    protected val remoteDataSource = RemoteDataSource()
    protected  lateinit var  viewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater,container)
        val factory = ViewModelFactory(geFragmentrepository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel())
        return binding.root
    }

    abstract  fun getViewModel() : Class<VM>

    abstract  fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?) :B

    abstract  fun geFragmentrepository(): R


}