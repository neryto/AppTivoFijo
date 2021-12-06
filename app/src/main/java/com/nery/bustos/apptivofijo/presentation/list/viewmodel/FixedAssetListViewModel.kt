package com.nery.bustos.apptivofijo.presentation.list.viewmodel

import androidx.lifecycle.viewModelScope
import com.nery.bustos.apptivofijo.base.BaseViewModel
import com.nery.bustos.apptivofijo.data.list.FixedAssetListRepository
import com.nery.bustos.apptivofijo.domain.list.FixedAssetListUseCase
import com.nery.bustos.apptivofijo.presentation.list.FixedAsset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FixedAssetListViewModel : BaseViewModel<List<FixedAsset>>(),
    FixedAssetListUseCase.ViewModelUSeCase {

  private lateinit var repository : FixedAssetListUseCase.RepositoryUseCase

    override fun getList() {
        repository = FixedAssetListRepository(viewModelScope)
        viewModelScope.launch(Dispatchers.IO) {
            repository.getList()?.onEach {
                _fetchInfo.postValue(it)
            }?.launchIn(viewModelScope)
        }
    }


}