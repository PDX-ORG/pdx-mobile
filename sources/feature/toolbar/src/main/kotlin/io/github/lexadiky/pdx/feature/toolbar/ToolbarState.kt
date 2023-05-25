package io.github.lexadiky.pdx.feature.toolbar

import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitString

data class ToolbarState(
    val title: StringResource = StringResource.from(
        UikitString.uikit_generic_app_title
    )
)
