package com.nery.bustos.apptivofijo.domain.list

import com.nery.bustos.apptivofijo.presentation.list.FixedAsset
import kotlinx.coroutines.flow.Flow

class FixedAssetListUseCase {
    interface ViewModelUSeCase{
        fun getList()
    }

    interface RepositoryUseCase{
        suspend fun getList() : Flow<List<FixedAsset>>?
    }
}