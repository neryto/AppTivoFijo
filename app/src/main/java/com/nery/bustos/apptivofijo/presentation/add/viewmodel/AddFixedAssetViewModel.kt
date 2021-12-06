package com.nery.bustos.apptivofijo.presentation.add.viewmodel

import androidx.lifecycle.viewModelScope
import com.nery.bustos.apptivofijo.base.BaseViewModel
import com.nery.bustos.apptivofijo.data.add.AddFixedAssetRepository
import com.nery.bustos.apptivofijo.domain.add.AddFixedAssetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFixedAssetViewModel : BaseViewModel<Nothing>() , AddFixedAssetUseCase.ViewModelUseCase {

   private lateinit var repository : AddFixedAssetUseCase.Repository

    override fun saveData(
        photo: String,
        name: String,
        type: String,
        description: String,
        date: String
    ) {

        repository = AddFixedAssetRepository(viewModelScope)

        viewModelScope.launch(Dispatchers.IO) {
            repository.saveData(photo, name, type, description, date)
        }
    }

}