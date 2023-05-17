package io.github.lexadiky.pdx.lib.locale

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val LocaleManagerModule by module("locale-manager") {
    single { LocaleManager(inject()) }
}
