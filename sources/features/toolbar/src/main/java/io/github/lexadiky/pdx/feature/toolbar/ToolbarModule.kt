package io.github.lexadiky.pdx.feature.toolbar

import io.github.lexadiky.pdx.lib.arc.di.module

val ToolbarModule by module("toolbar") {
    internal {
        viewModel { params -> ToolbarViewModel(params.get()) }
    }
}