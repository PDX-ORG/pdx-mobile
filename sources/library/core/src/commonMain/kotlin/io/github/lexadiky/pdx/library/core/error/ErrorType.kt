package io.github.lexadiky.pdx.library.core.error

sealed interface ErrorType {

    interface Any : ErrorType

    interface Network : ErrorType, Any

    interface Generic : ErrorType, Any
}
