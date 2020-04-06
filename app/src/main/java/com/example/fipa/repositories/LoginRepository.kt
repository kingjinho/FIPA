package com.example.fipa.repositories

import com.example.fipa.models.User
import com.example.fipa.network.FIPA_NETWORK
import com.example.fipa.utils.HTTPS_INTERNAL_EROR
import com.example.fipa.utils.HTTPS_SUCCESS
import kotlinx.coroutines.launch

/**
 * Created by KING JINHO on 2020-04-06
 */
class LoginRepository() : BaseRepository(){



    fun loginRequest(user: User) : Int {
        var statusCode = HTTPS_SUCCESS
        getScope().launch {
            statusCode = try {
                var result = FIPA_NETWORK.REMOTE_SERVICE.loginRequest(user)
                if (result.isSuccessful) {

                }
                result.code()
            } catch (e: Exception) {
                HTTPS_INTERNAL_EROR
            }
        }
        return statusCode
    }

    fun cancelViewModel() {
        getViewModelJob().cancel()
    }
}