package io.github.lexadiky.pdx.feature.pokemon.details.toggle

import io.github.lexadiky.pdx.library.featuretoggle.BooleanFeatureToggle

val SpritesViewerFeatureToggle = BooleanFeatureToggle(
    id = "sprites_viewer",
    group = "pokemon_details",
    defaultValue = false
)
