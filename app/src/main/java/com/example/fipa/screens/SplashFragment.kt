package com.example.fipa.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.fipa.R
import com.example.fipa.databinding.FragmentSplashBinding
import com.example.fipa.extensions.inTransaction
import com.example.fipa.utils.Settings
import com.example.fipa.viewmodels.StartUpViewModel

/**
 * Created by KING JINHO on 2020-01-06
 */

class SplashFragment : Fragment() {
    private val EMAIL = "EMAIL"
    private lateinit var mSplashFragmentBindingUtil: FragmentSplashBinding
    private lateinit var mStartUpViewModel: StartUpViewModel
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            Settings.AUTHENTICATION,
            masterKeyAlias,
            activity!!.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

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
        mSplashFragmentBindingUtil =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        mStartUpViewModel = ViewModelProvider(this).get(StartUpViewModel::class.java)
        mSplashFragmentBindingUtil.startUpViewModel = mStartUpViewModel
        mSplashFragmentBindingUtil.lifecycleOwner = this

        mStartUpViewModel.isAuthenticated.observe(viewLifecycleOwner, Observer {
            if (it)
                activity!!.supportFragmentManager.inTransaction { replace(R.id.fragment_layout, LoginFragment()) }
        })
        Settings.EMAIL = sharedPreferences.getString(EMAIL, "")!!
        if (Settings.EMAIL.isEmpty()) {
            activity!!.supportFragmentManager.inTransaction {
                replace(
                    R.id.fragment_layout,
                    LoginFragment()
                )
            }
        } else
            mStartUpViewModel.getLoginStatus(Settings.EMAIL)


        return mSplashFragmentBindingUtil.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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