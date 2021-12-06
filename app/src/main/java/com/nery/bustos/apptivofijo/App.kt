package com.nery.bustos.apptivofijo

import android.app.Application
import androidx.room.Room
import com.nery.bustos.apptivofijo.db.FixedAssetDatabase

class App : Application() {
    companion object {
        var db: FixedAssetDatabase? = null

        fun getDatabase(): FixedAssetDatabase? {
            return db
        }
    }

    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(
            applicationContext,
            FixedAssetDatabase::class.java,
            "FixedAssetDatabase").build()
    }
}