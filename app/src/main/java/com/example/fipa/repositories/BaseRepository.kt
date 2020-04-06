package com.example.fipa.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by KING JINHO on 2020-04-06
 */
open class BaseRepository {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getViewModelJob() : Job {
        return viewModelJob
    }

    fun getScope() : CoroutineScope {
        return coroutineScope
    }
}