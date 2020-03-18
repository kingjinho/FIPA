package com.example.fipa.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fipa.BR
import com.example.fipa.models.User
import com.example.fipa.network.FIPAApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by KING JINHO on 2020-02-09
 */

class LoginViewModel : BaseObservableViewModel() {


    private lateinit var user: User

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    var _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    var _businessLicense = MutableLiveData<String>()
    val businessLicense: LiveData<String>
        get() = _password

    var _btnFindPassword = MutableLiveData<Boolean>()
    val btnFindPassword: LiveData<Boolean>
        get() = _btnFindPassword

    var _btnSignup = MutableLiveData<Boolean>()
    val btnSignup: LiveData<Boolean>
        get() = _btnSignup

    var _btnLogin = MutableLiveData<Boolean>()
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

    @Bindable
    fun getEmail(): String {
        return email.value!!
    }

    fun setEmail(email: String) {
        _email.value = email

    }

    fun getPassword(): String {
        return password.value!!
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun gettBusinessLicense() : String {
        return  businessLicense.value!!
    }

    fun setBusinessLicense(businessLicense: String) {
        _businessLicense.value = businessLicense
    }

    fun onFindPasswordClick() {
        _btnFindPassword.value = true
    }

    fun onAfterFindPassworClick() {
        _btnFindPassword.value = false
    }

    fun onSignupClick() {
        _btnSignup.value = true
    }

    fun onAfterSignupClick() {
        _btnSignup.value = false
    }

    fun onLoginClick() {
        user = User()
        user.setEmail(email.toString())
        coroutineScope.launch {
            try {
                var loginResult = FIPAApi.retrofitService.getAuthenticationStatus(user.getEmail())
                if (loginResult.isSuccessful)
                    _btnLogin.value = true
            } catch (e: Exception) {
                _btnLogin.value = false
            }
        }
    }

    fun onLoginComplete() {
        _btnLogin.value = false
    }
}