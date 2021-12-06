package com.nery.bustos.apptivofijo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FixedAssetEntity::class], version = 1)
abstract class FixedAssetDatabase : RoomDatabase() {
    abstract fun fixedAssetDao(): FixedAssetDao
}