package com.nery.bustos.apptivofijo.data.add

import com.nery.bustos.apptivofijo.base.BaseRepository
import com.nery.bustos.apptivofijo.db.FixedAssetEntity
import com.nery.bustos.apptivofijo.domain.add.AddFixedAssetUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFixedAssetRepository
    constructor(viewModelScope: CoroutineScope)
    : BaseRepository(viewModelScope), AddFixedAssetUseCase.Repository {

    override suspend fun saveData(
        photo: String,
        name: String,
        type: String,
        description: String,
        date: String
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            dao?.insertFixedAsset(
                FixedAssetEntity(
                    name = name,
                    description = description,
                    classification = type,
                    acquisitionDate = date,
                    photo = photo
                )
            )
        }

    }
}