package com.example.fipa.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fipa.R
import com.example.fipa.databinding.FragmentLoginBinding
import com.example.fipa.extensions.inTransaction
import com.example.fipa.models.User
import com.example.fipa.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by KING JINHO on 2020-01-12
 */
class LoginFragment : Fragment() {

    private lateinit var mLoginFragmentBindingUtil: FragmentLoginBinding
    private lateinit var mLoginViewModel: LoginViewModel

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
        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mLoginFragmentBindingUtil.loginViewModel = mLoginViewModel
        mLoginFragmentBindingUtil.lifecycleOwner = this
        /*mLoginViewModel.email.observe(
            viewLifecycleOwner,
            Observer { text -> mLoginViewModel.setEmail(text) })*/
//        mLoginViewModel.businessLicense.observe(
//            viewLifecycleOwner,
//            Observer { text -> mLoginViewModel.setBusinessLicense(text) })
//        mLoginViewModel.password.observe(
//            viewLifecycleOwner,
//            Observer { text -> mLoginViewModel.setPassword(text) })
//
        mLoginViewModel.btnLogin.observe(viewLifecycleOwner, Observer { loginSuccessful ->
            if (loginSuccessful) {
                activity!!.supportFragmentManager.inTransaction {
                    replace(
                        R.id.fragment_layout,
                        MainFragment()
                    )
                }
                mLoginViewModel.onLoginComplete()
            }
        })
        mLoginViewModel.btnFindPassword.observe(viewLifecycleOwner, Observer { isClicked ->
            if (isClicked) {
                activity!!.supportFragmentManager.inTransaction {
                    replace(
                        R.id.fragment_layout,
                        ResetPasswordFragment()
                    )
                }
                mLoginViewModel.onAfterFindPassworClick()
            }
        })
        mLoginViewModel.btnSignup.observe(viewLifecycleOwner, Observer { isClicked ->
            if (isClicked) {
                activity!!.supportFragmentManager.inTransaction {
                    replace(
                        R.id.fragment_layout,
                        SignupFragment()
                    )
                }
                mLoginViewModel.onAfterSignupClick()
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