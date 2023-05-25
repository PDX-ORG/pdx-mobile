package io.github.lexadiky.pdx.feature.rateapp

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.generated.analytics.RateAppEventsSpec

internal val RateAppModule by module("feature-rate-app") {
    internal {
        single { RateAppEventsSpec(inject()) }
        singleViewModel { RateAppSocket(inject(), inject(), inject()) }
    }
}
