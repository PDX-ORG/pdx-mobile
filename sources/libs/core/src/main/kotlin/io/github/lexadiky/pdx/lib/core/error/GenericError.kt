package io.github.lexadiky.pdx.lib.core.error

class GenericError private constructor(message: String, cause: Throwable?) :
    ErrorType.Generic, Throwable(message, cause) {

    companion object {

        fun originate(message: String): GenericError = GenericError(message, null)

        operator fun invoke(message: String, cause: Throwable): GenericError {
            return GenericError(message, cause)
        }
    }
}
