package io.github.lexadiky.pdx.feature.ability.details

import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.empty

data class AbilityDetailsState(
    val title: StringResource = StringResource.empty(),
    val subtitle: StringResource = StringResource.empty(),
    val effect: StringResource = StringResource.empty(),
    val error: UIError? = null
)
