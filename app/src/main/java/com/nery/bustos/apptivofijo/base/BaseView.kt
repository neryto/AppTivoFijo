package com.nery.bustos.apptivofijo.base

import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding

abstract class BaseView <ViewBindingType:ViewBinding , T :Enum<T>>
    constructor(val actionHandler:(action:T,value:Any?)->Unit) : DefaultLifecycleObserver,
    ViewModelStoreOwner {

    private val appViewModelStore: ViewModelStore by lazy {ViewModelStore()}

    protected lateinit var lifecycleOwner: LifecycleOwner
    protected var binding : ViewBindingType? = null
    protected val mBinding
    get() = requireNotNull(binding)

    open fun init(lifecycleOwner: LifecycleOwner){
        initObservers()
    }
    abstract fun initObservers()
    abstract fun setupViewBinding(view: View) : ViewBindingType

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

}