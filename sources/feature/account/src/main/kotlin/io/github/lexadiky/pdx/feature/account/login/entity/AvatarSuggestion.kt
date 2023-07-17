package io.github.lexadiky.pdx.feature.account.login.entity

import io.github.lexadiky.pdx.library.resources.image.ImageResource

internal data class AvatarSuggestion(
    val resource: ImageResource,
    val uri: String,
)
