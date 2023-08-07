package io.github.lexadiky.pdx.feature.drawer.entity

import io.github.lexadiky.pdx.library.featuretoggle.BooleanFeatureToggle

internal val NewsInDrawerFeatureToggle = BooleanFeatureToggle(
    id = "news_enabled",
    group = "drawer",
    defaultValue = false
)
