package com.droid.authexample.ui.auth.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.droid.authexample.data.network.Resource
import com.droid.authexample.data.network.UserApi
import com.droid.authexample.data.repository.UserRepository
import com.droid.authexample.data.responses.User
import com.droid.authexample.databinding.FragmentHomeBinding
import com.droid.authexample.ui.auth.base.BaseFragment
import com.droid.authexample.ui.auth.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding,UserRepository>() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading ->{
                    binding.progressbar.visible(true)
                }
            }
        })
    }

    private fun updateUI(user :User ) {
        with(binding){
            textViewEmail.text = user.email
            textViewName.text =user.name
            textViewId.text =user.id.toString()
        }
    }



    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater,container,false)


    override fun geFragmentrepository() :UserRepository{
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java)
        return  UserRepository(api)
    }

}