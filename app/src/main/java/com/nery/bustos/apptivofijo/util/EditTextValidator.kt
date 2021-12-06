package com.nery.bustos.apptivofijo.util

import android.widget.EditText

object EditTextValidator {
    fun validate(vararg edts:EditText):Boolean{
        var result = true
        edts.onEach {
            if (it.text.toString().isEmpty()){
                it.error = "Dato requerido"
                it.requestFocus()
                result = false
            }
        }
        return result
    }
}