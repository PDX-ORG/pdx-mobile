package io.github.lexadiky.pdx.feature.toolbar

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.viewModel

val ToolbarModule by module("toolbar") {
    internal {
        viewModel { params -> ToolbarViewModel(params.get()) }
    }
}
