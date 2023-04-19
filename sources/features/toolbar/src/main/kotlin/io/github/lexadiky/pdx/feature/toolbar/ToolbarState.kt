package io.github.lexadiky.pdx.feature.toolbar

import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

data class ToolbarState(
    val title: StringResource = StringResource.from(
        io.github.lexadiky.pdx.lib.uikit.R.string.uikit_generic_app_title
    )
)
