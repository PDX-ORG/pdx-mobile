package io.github.lexadiky.pdx.lib.dynbanner

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.lib.dynbanner.domain.DynamicBannerRepository

val DynamicBannerModule by module("dynamic-banner") {
    single { DynamicBannerRepository(inject()) }
    singleViewModel { params ->
        DynamicBannerViewModel(
            params.get(),
            inject(),
            inject()
        )
    }
}
