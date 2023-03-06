package com.splanes.apps.skillbook.domain.common.error

sealed class KnownException(override val message: String? = null) : Throwable()

object UnhandledError : KnownException()

class ConfigFileNotFound(
    val filename: String,
    override val message: String? = null
) : KnownException(message)
