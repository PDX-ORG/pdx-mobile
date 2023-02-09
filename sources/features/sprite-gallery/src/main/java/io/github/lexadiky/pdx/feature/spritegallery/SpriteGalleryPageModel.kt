package io.github.lexadiky.pdx.feature.spritegallery

import io.github.lexadiky.akore.alice.module

val SpriteGalleryPageModel by module("feature-sprite-gallery") {
    internal {
        single { args -> SpriteGalleryPageViewModel(args.get(), args.get(), inject()) }
    }
}
