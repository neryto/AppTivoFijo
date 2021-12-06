package com.nery.bustos.apptivofijo.presentation.list.ui

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*


class DateDialog
    constructor(context: Context,
                onDate : (date:String)->Unit){
   private val calendar = Calendar.getInstance()
    init {
        DatePickerDialog(
            context,
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val format = "MM/dd/yy"
                val sdf = SimpleDateFormat(format, Locale.US)
                onDate.invoke(sdf.format(calendar.time))

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

    }



}