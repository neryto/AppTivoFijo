package com.nery.bustos.apptivofijo.domain.add

class AddFixedAssetUseCase {
    interface ViewModelUseCase{
        fun saveData(
            photo:String,
            name:String,
            type:String,
            description:String,
            date:String)
    }

    interface Repository{
        suspend fun saveData(
            photo:String,
            name:String,
            type:String,
            description:String,
            date:String)
    }
}