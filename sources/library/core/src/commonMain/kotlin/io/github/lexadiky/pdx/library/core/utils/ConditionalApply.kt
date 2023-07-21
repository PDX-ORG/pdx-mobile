package io.github.lexadiky.pdx.library.core.utils

inline fun <T> T.applyIf(condition: Boolean, application: T.() -> T): T {
    if (condition) {
        return application(this)
    } else {
        return this
    }
}