@file:OptIn(ExperimentalContracts::class)

package io.github.lexadiky.lib.blogger

import kotlin.contracts.ExperimentalContracts

interface LoggerDelegate {

    fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?)

    fun tag(tag: String): ContextualLoggerDelegate
}
