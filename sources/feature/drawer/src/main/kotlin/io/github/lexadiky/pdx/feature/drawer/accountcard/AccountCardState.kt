package io.github.lexadiky.pdx.feature.drawer.accountcard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import io.github.lexadiky.pdx.feature.drawer.R
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.resources.from

internal data class AccountCardState(
    val username: StringResource = StringResource.from(R.string.drawer_account_card_username_default),
    val avatar: ImageResource = DEFAULT_AVATAR,
    val isLoggedIn: Boolean = false
) {

    companion object {

        val DEFAULT_AVATAR = ImageResource.from(Icons.Default.Person)
    }
}