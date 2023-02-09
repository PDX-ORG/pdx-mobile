package io.github.lexadiky.pdx.feature.drawer

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.drawer.domain.DrawerItemSource

internal val DrawerModule by module("drawer") {
    internal {
        single { DrawerItemSource(inject()) }
        viewModel { DrawerViewModel(inject(), inject()) }
    }
}
