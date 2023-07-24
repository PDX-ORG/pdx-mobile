package io.github.lexadiky.pdx.feature.account.details

import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.placeholder

internal data class AccountDetailsState(
    val username: String? = null,
    val avatar: ImageResource = ImageResource.placeholder(),
    val localTrainerId: String? = null
)
