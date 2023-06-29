package io.github.lexadiky.pdx.feature.drawer.accountcard

import io.github.lexadiky.pdx.feature.drawer.R
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

internal data class AccountCardState(
    val username: StringResource = StringResource.from(R.string.drawer_account_card_username_default),
    val isLoggedIn: Boolean = false
)