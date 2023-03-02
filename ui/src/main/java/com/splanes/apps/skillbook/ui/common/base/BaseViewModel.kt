package com.splanes.apps.skillbook.ui.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splanes.apps.skillbook.domain.common.base.UseCase
import com.splanes.apps.skillbook.domain.common.error.KnownException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel() : ViewModel() {
    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }

    fun <T> Flow<T>.eagerly(initial: T) = stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initial
    )

    fun <T> UseCase.Result<T>.onSuccess(block: (T) -> Unit) = apply {
        (this as? UseCase.Success)?.data?.run(block)
    }

    fun <T> UseCase.Result<T>.onError(block: (KnownException) -> Unit) = apply {
        (this as? UseCase.Error)?.cause?.run(block)
    }
}
