package com.nery.bustos.apptivofijo.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.Exception

open class BaseActivity : AppCompatActivity() {

    protected fun addFragment(container: Int, fragment: Fragment, tag: String) {
        try {
            supportFragmentManager.beginTransaction().add(container, fragment, tag).commit()
        }catch (e:Exception){

        }
    }

    protected fun changeToFragment(container: Int, fragment: Fragment, tag: String){
        hideCurrentFragment(container)
        addFragment(container, fragment, tag)
    }

    protected fun getCurrentFragment(container: Int):Fragment? =
        supportFragmentManager.findFragmentById(container)



    private fun hideCurrentFragment(container: Int){
        getCurrentFragment(container)?.let {
            supportFragmentManager.beginTransaction().hide(it).commit()
        }
    }

    protected fun returnToFragment(tag: String,container: Int){
        supportFragmentManager.apply {
            getCurrentFragment(container)?.let { current->
                beginTransaction().remove(current).commitNow()
                findFragmentByTag(tag)?.let {fragment->
                    beginTransaction().show(fragment).commitNow()
                }
            }

        }

    }

}