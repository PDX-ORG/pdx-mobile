package io.github.lexadiky.pdx.feature.account.details

import io.github.lexadiky.pdx.feature.account.details.entity.CandyBoxState
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.placeholder

internal data class AccountDetailsState(
    val username: String? = null,
    val avatar: ImageResource = ImageResource.placeholder(),
    val localTrainerId: String? = null,
    val candyBoxes: Lce<GenericError, List<CandyBoxState>> = Lce.Loading
)
