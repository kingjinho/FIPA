package com.example.fipa.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fipa.models.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by KING JINHO on 2020-02-09
 */

class LoginViewModel : ViewModel() {

    private lateinit var login: Login

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private var _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private var _businessLicense = MutableLiveData<String>()
    val businessLicense: LiveData<String>
        get() = _password

    private var _btnFindPassword = MutableLiveData<Boolean>()
    val btnFindPassword: LiveData<Boolean>
        get() = _btnFindPassword

    private var _btnSignup = MutableLiveData<Boolean>()
    val btnSignup: LiveData<Boolean>
        get() = _btnSignup

    private var _btnLogin = MutableLiveData<Boolean>()
    val btnLogin: LiveData<Boolean>
        get() = _btnLogin

    init {
        clear()
        _btnFindPassword.value = false
        _btnSignup.value = false
        _btnLogin.value = false
    }

    private fun clear() {
        _email.value = ""
        _password.value = ""
        _businessLicense.value = ""
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setBusinessLicense(businessLicense: String) {
        _businessLicense.value = businessLicense
    }

    fun onFindPasswordClick() {
        _btnFindPassword.value = true
    }

    fun onSignupClick() {
        _btnSignup.value = true
    }

    fun onLoginClick() {
        _btnLogin.value = true
    }

    fun onLoginComplete() {
        _btnLogin.value = false
    }
}