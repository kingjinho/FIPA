package com.example.fipa.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.fipa.R
import com.example.fipa.databinding.FragmentLoginBinding
import com.example.fipa.extensions.inTransaction
import com.example.fipa.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by KING JINHO on 2020-01-12
 */
class LoginFragment : Fragment() {

    private lateinit var mLoginFragmentBindingUtil: FragmentLoginBinding

    private lateinit var mViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLoginFragmentBindingUtil =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mLoginFragmentBindingUtil.loginViewModel = mViewModel
        mLoginFragmentBindingUtil.lifecycleOwner = this
        mViewModel.email.observe(viewLifecycleOwner, Observer { text ->
            mViewModel.setEmail(text)
        })
        mViewModel.businessLicense.observe(viewLifecycleOwner, Observer { text ->
            mViewModel.setBusinessLicense(text)
        })
        mViewModel.password.observe(viewLifecycleOwner, Observer { text ->
            mViewModel.setPassword(text)
        })

        mViewModel.btnLogin.observe(viewLifecycleOwner, Observer { loginSuccessful ->
            if (loginSuccessful) {
                activity!!.supportFragmentManager.inTransaction { replace(R.id.fragment_host, MainFragment()) }
                mViewModel.onLoginComplete()
            }
        })

        return mLoginFragmentBindingUtil.root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

}