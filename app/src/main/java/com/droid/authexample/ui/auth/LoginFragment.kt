package com.droid.authexample.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.droid.authexample.databinding.FragmentLoginBinding
import com.droid.authexample.data.network.AuthApi
import com.droid.authexample.data.network.Resource
import com.droid.authexample.data.repository.AuthRepository
import com.droid.authexample.ui.auth.base.BaseFragment
import com.droid.authexample.ui.auth.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.launch


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
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Login Failure",Toast.LENGTH_LONG).show()
                }
            }

        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }


        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            binding.progressbar.visible(true)

            viewModel.login(email, password)

        }

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun geFragmentrepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}

