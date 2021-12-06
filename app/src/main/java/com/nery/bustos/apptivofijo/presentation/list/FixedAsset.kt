package com.nery.bustos.apptivofijo.presentation.list

data class FixedAsset(
    val name:String,
    val photo : String = "",
    val type:String,
    val description:String,
    val date : String
)
