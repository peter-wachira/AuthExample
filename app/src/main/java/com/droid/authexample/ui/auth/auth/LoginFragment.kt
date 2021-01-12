package com.droid.authexample.ui.auth.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.droid.authexample.databinding.FragmentLoginBinding
import com.droid.authexample.data.network.AuthApi
import com.droid.authexample.data.network.Resource
import com.droid.authexample.data.repository.AuthRepository
import com.droid.authexample.ui.auth.base.BaseFragment
import com.droid.authexample.ui.auth.enable
import com.droid.authexample.ui.auth.handleApiError
import com.droid.authexample.ui.auth.home.HomeActivity
import com.droid.authexample.ui.auth.startNewActivity
import com.droid.authexample.ui.auth.visible
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java
    override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.progressbar.visible(false)
            when(it){

                is Resource.Success->{

                        viewModel.saveAuthToken(it.value.user.access_token!!)
                     requireActivity().startNewActivity(HomeActivity::class.java)

                }
                is Resource.Failure -> handleApiError(it){ login()}
            }

        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }


        binding.buttonLogin.setOnClickListener {
            login()
        }


    }

    private  fun login(){
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        binding.progressbar.visible(true)
        viewModel.login(email, password)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun geFragmentrepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}

