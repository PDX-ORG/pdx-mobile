package io.github.lexadiky.pdx.feature.account.login

import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.resources.image.UrlImageResource
import java.util.UUID

data class LoginPageState(
    val username: String = "",
    val inputEnabled: Boolean = true,
    val usernameIsInvalid: Boolean = false,
    val selectedAvatar: UrlImageResource? = null,
    val availableAvatars: Lce<GenericError, List<UrlImageResource>> = Lce.Loading
) {

    val trainerId: UUID = UUID.randomUUID()
    val localTrainerId: String = trainerId.toString().uppercase()
}