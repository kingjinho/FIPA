package com.example.fipa.viewmodels

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry

import androidx.lifecycle.ViewModel

/**
 * Created by KING JINHO on 2020-03-18
 */
open class BaseObservableViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    @Suppress("unused")
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null) //notify listeners that this instance have changed
    }

    //notify listeners that a specific property has changed
    fun notifyPropertyChange(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}