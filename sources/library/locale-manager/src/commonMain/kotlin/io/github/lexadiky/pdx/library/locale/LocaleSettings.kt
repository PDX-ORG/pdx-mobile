package io.github.lexadiky.pdx.library.locale

import java.util.Locale

data class LocaleSettings(
    val system: Locale,
    val flags: List<LocaleFlag>
) {

    fun has(flag: LocaleFlag): Boolean {
        return flag in flags
    }
}

@JvmInline
value class LocaleFlag(val name: String)
