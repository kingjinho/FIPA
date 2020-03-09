package com.example.fipa.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fipa.network.FIPAApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by KING JINHO on 2020-03-09
 */
class StartUpViewModel : ViewModel() {

    private var _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean>
        get() = _isAuthenticated

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _isAuthenticated.value = false
    }

    fun getLoginStatus(email: String) {
        coroutineScope.launch {
            var authenticationResult = FIPAApi.retrofitService.getAuthenticationStatus(email)
            if (authenticationResult.isSuccessful)
                _isAuthenticated.value = true
        }
    }

}