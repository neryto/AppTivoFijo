package com.nery.bustos.apptivofijo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel<T> : ViewModel() {


    protected val _fetchInfo: MutableLiveData<T> = MutableLiveData()

    open val fetchInfo: LiveData<T>
        get() = _fetchInfo


}