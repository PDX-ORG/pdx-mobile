package io.github.lexadiky.pdx.feature.move.details

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel

val MoveDetailsModule by module("feature-move-details") {
    internal {
        singleViewModel { args -> MoveDetailsViewModel(args.get()) }
    }
}