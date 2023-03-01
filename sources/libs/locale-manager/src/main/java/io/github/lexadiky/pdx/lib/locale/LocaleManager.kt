package io.github.lexadiky.pdx.lib.locale

import io.github.lexadiky.pdx.lib.fs.FsManager
import java.util.Locale

class LocaleManager(fsManager: FsManager) {

    private var flags by fsManager.atomic("pdx_locale").stringSet("locale_flags", emptySet())

    val settings: LocaleSettings get() = LocaleSettings(
        system = Locale.ENGLISH,
        flags = flags.map { LocaleFlag(it) }
    )

    fun addFlag(flag: LocaleFlag) {
        flags = flags + flag.name
    }

    fun removeFlag(flag: LocaleFlag) {
        flags = flags - flag.name
    }
}