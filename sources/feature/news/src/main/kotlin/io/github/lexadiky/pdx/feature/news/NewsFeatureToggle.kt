package io.github.lexadiky.pdx.feature.news

import io.github.lexadiky.pdx.library.featuretoggle.BooleanFeatureToggle

val NewsFeatureToggle = BooleanFeatureToggle(
    group = "news",
    id = "navigation_enabled",
    defaultValue = true
)
