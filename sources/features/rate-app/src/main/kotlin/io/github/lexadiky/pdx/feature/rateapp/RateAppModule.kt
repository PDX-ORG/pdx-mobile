package io.github.lexadiky.pdx.feature.rateapp

import com.google.android.play.core.review.ReviewManagerFactory
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single

internal val RateAppModule by module("feature-rate-app") {
    single { ReviewManagerFactory.create(inject()) }

    internal {
        singleViewModel { RateAppSocket(inject(), inject()) }
    }
}
