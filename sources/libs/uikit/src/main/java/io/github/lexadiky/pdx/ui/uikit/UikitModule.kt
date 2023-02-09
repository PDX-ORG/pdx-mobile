package io.github.lexadiky.pdx.ui.uikit

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.pdx.ui.uikit.util.ShakeDetector

val UikitModule by module("uikit") {
    single { ShakeDetector(inject()) }
}
