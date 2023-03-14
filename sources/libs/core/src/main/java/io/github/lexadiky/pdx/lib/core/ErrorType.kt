package io.github.lexadiky.pdx.lib.core

sealed interface ErrorType {

    interface Any : ErrorType

    interface Network : ErrorType, Any

    interface Generic : ErrorType, Any
}