package com.nery.bustos.apptivofijo.db

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nery.bustos.apptivofijo.presentation.list.FixedAsset
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class FixedAssetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "classification")
    val classification: String,

    @ColumnInfo(name = "acquisition_date")
    val acquisitionDate: String,

    @ColumnInfo(name = "registration_date")
    val registrationDate: String = currentDate(),

    @ColumnInfo(name = "photo")
    val photo: String,


    ) {
    val toFixedAsset
        get() = FixedAsset(
            name = this.name,
            photo = this.photo,
            type = this.classification,
            description = this.description,
            date = this.acquisitionDate
        )
}


@SuppressLint("SimpleDateFormat")
private fun currentDate(): String {
    val sdf = SimpleDateFormat("MM/dd/yy")
    return sdf.format(Date())
}