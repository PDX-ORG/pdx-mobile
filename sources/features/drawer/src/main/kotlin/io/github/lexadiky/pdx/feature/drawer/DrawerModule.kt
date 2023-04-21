package io.github.lexadiky.pdx.feature.drawer

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.drawer.domain.DrawerItemSource
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single

internal val DrawerModule by module("drawer") {
    internal {
        single { DrawerItemSource(inject(), inject()) }
        singleViewModel { params -> DrawerViewModel(inject(), inject()) }
    }
}
