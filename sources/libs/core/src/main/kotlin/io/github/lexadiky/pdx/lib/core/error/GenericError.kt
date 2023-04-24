package io.github.lexadiky.pdx.lib.core.error

class GenericError(message: String, cause: Throwable? = null) : ErrorType.Generic, Throwable(
    message, cause
)
