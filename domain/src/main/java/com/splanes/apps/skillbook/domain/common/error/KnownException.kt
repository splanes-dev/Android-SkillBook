package com.splanes.apps.skillbook.domain.common.error

sealed class KnownException : Throwable()

object UnhandledError : KnownException()
