package io.github.lexadiky.pdx.lib.core.error

class GenericError constructor(message: String, cause: Throwable) :
    ErrorType.Generic, Throwable(message, cause) {

    companion object {

        @Suppress("ThrowingExceptionsWithoutMessageOrCause")
        fun originate(message: String): GenericError = GenericError(message, Throwable())
    }
}
