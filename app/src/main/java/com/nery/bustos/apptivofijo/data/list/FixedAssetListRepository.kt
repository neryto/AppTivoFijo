package com.nery.bustos.apptivofijo.data.list

import com.nery.bustos.apptivofijo.base.BaseRepository
import com.nery.bustos.apptivofijo.domain.list.FixedAssetListUseCase
import com.nery.bustos.apptivofijo.presentation.list.FixedAsset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FixedAssetListRepository(
    viewModelScope: CoroutineScope
) : BaseRepository(viewModelScope), FixedAssetListUseCase.RepositoryUseCase {

    override suspend fun getList() : Flow<List<FixedAsset>>? =
          dao?.getAll()?.map { list->
                list.map {
                    it.toFixedAsset
                }
            }
}