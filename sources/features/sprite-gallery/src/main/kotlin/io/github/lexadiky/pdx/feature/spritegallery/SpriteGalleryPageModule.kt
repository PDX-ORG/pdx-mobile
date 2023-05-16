package io.github.lexadiky.pdx.feature.spritegallery

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel

val SpriteGalleryPageModule by module("feature-sprite-gallery") {
    internal {
        singleViewModel { args -> SpriteGalleryPageViewModel(args.get(), inject()) }
    }
}
