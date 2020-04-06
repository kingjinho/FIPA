package com.example.fipa.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fipa.models.User
import com.example.fipa.repositories.LoginRepository

/**
 * Created by KING JINHO on 2020-02-09
 */

class LoginViewModel(private val loginRepo: LoginRepository) : BaseObservableViewModel() {


    private lateinit var user: User



    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var license = ObservableField<String>()

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
        email.set("")
        password.set("")
        license.set("")
    }

    fun getEmail() : String {
        return email.get().toString()
    }

    fun setEmail(text: String) {
        email.set(text)
    }

    fun getPassword() : String {
        return password.get().toString()
    }

    fun setPassword(text: String) {
        password.set(text)
    }

    fun getLicense() : String {
        return license.get().toString()
    }

    fun setLicense(text: String) {
        license.set(text)
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
        user = User(getEmail(), getPassword(), getLicense())
        val result = loginRepo.loginRequest(user)

    }

    fun onLoginComplete() {
        _btnLogin.value = false
    }

    override fun onCleared() {
        super.onCleared()
        loginRepo.cancelViewModel()
    }
}