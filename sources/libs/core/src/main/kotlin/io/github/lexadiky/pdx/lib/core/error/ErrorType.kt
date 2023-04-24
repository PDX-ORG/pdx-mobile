package io.github.lexadiky.pdx.lib.core.error

sealed interface ErrorType {

    interface Any : ErrorType

    interface Network : ErrorType, Any

    interface Generic : ErrorType, Any
}
