package com.nery.bustos.apptivofijo.base

import com.nery.bustos.apptivofijo.App.Companion.getDatabase
import kotlinx.coroutines.CoroutineScope

open class BaseRepository
constructor(protected val viewModelScope: CoroutineScope) {
    protected val dao = getDatabase()?.fixedAssetDao()
}