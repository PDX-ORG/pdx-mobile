package io.github.lexadiky.pdx.lib.locale

import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import java.util.Locale

class LocaleManager(microdataManager: MicrodataManager) {
    private val microdata = microdataManager.acquire(this, "pdx_locale")

    private var flags = microdata.strings("locale_flags")

    val settings: LocaleSettings get() = LocaleSettings(
        system = Locale.getDefault(),
        flags = flags.get()?.map { LocaleFlag(it) }
            .orEmpty()
    )

    suspend fun addFlag(flag: LocaleFlag) {
        flags.set(flags.get().orEmpty() + flag.name)
    }

    suspend fun removeFlag(flag: LocaleFlag) {
        flags.set(flags.get().orEmpty() - flag.name)
    }
}