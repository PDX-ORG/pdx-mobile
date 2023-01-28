package io.github.lexadiky.pdx.feature.toolbar

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.alice.module

val ToolbarModule by module("toolbar") {
    internal {
        viewModel { params -> ToolbarViewModel(params.get()) }
    }
}