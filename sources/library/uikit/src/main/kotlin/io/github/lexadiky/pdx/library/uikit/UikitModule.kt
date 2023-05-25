package io.github.lexadiky.pdx.library.uikit

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.library.uikit.util.ShakeDetector

val UikitModule by module("uikit") {
    single { ShakeDetector(inject()) }
}
