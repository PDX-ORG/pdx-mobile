package io.github.lexadiky.pdx.feature.rateapp

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel

internal val RateAppModule by module("feature-rate-app") {
    internal {
        singleViewModel { RateAppSocket(inject(), inject()) }
    }
}
