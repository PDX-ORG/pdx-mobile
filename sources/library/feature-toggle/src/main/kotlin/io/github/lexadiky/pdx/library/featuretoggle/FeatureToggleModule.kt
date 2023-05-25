package io.github.lexadiky.pdx.library.featuretoggle

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val FeatureToggleModule by module("feature-toggle") {
    single { FeatureToggleManager(inject(), inject()) }
}
