package io.github.lexadiky.pdx.feature.drawer.entity

import io.github.lexadiky.pdx.library.featuretoggle.BooleanFeatureToggle

internal val AuthInDrawerFeatureToggle = BooleanFeatureToggle(
    id = "auth_in_drawer",
    group = "drawer",
    defaultValue = false
)
