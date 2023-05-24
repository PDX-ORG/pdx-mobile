package io.github.lexadiky.pdx.library.dynbanner

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.library.dynbanner.domain.DynamicBannerRepository

val DynamicBannerModule by module("dynamic-banner") {
    single { DynamicBannerRepository(inject()) }
    singleViewModel { params ->
        DynamicBannerViewModel(
            params.get(),
            params.get(),
            inject(),
            inject()
        )
    }
}
