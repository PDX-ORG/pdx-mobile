package io.github.lexadiky.pdx.feature.toolbar

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel

val ToolbarModule by module("toolbar") {
    internal {
        singleViewModel { params ->
            ToolbarViewModel(params.get<Any>() as ToolbarConnector)
        }
    }
}