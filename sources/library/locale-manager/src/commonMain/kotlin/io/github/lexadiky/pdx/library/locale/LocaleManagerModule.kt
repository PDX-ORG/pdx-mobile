package io.github.lexadiky.pdx.library.locale

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val LocaleManagerModule by module("locale-manager") {
    single { LocaleManager(inject()) }
}
