package io.github.lexadiky.pdx.lib

import io.github.lexadiky.akore.alice.module

val FeatureToggleModule by module("feature-toggle") {
    single { FeatureToggleManager() }
}