package com.nery.bustos.apptivofijo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FixedAssetDao {
    @Query("SELECT * FROM fixedassetentity")
    fun getAll(): Flow<List<FixedAssetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFixedAsset(fixedAssetEntity: FixedAssetEntity)


}