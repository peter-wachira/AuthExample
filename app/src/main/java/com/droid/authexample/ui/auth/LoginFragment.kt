package com.droid.authexample.ui.auth

import android.os.BaseBundle
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.droid.authexample.R
import com.droid.authexample.databinding.FragmentLoginBinding
import com.droid.authexample.network.AuthApi
import com.droid.authexample.network.Resource
import com.droid.authexample.repository.AuthRepository
import com.droid.authexample.ui.auth.base.BaseFragment
import java.util.*


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {
    override fun getViewModel() = AuthViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Success->{
                    Toast.makeText(requireContext(), it.toString(),Toast.LENGTH_LONG).show()
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Login Failure",Toast.LENGTH_LONG).show()
                }
            }

        })

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            //@todo add validations
            viewModel.login(email, password)

        }


    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun geFragmentrepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))



}

