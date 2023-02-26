package com.splanes.apps.skillbook.domain.common.base

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.domain.common.error.UnhandledError

abstract class UseCase<T, R> {

    suspend operator fun invoke(params: T): Result<R> =
        try {
            val data = execute(params)
            Success(data)
        } catch (error: KnownException) {
            Error(error)
        } catch (error: Throwable) {
            Error(UnhandledError)
        }

    abstract suspend fun execute(params: T): R

    sealed class Result<out T>
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val cause: KnownException) : Result<Nothing>()
}
