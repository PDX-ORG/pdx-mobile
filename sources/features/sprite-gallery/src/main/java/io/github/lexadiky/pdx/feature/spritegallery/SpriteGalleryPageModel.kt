package io.github.lexadiky.pdx.feature.spritegallery

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val SpriteGalleryPageModule by module("feature-sprite-gallery") {
    internal {
        single { args -> SpriteGalleryPageViewModel(args.get(), args.get(), inject()) }
    }
}
