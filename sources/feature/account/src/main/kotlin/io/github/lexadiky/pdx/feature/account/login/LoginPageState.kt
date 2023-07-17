package io.github.lexadiky.pdx.feature.account.login

import io.github.lexadiky.pdx.feature.account.login.entity.AvatarSuggestion
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.lce.Lce
import java.util.UUID

internal data class LoginPageState(
    val username: String = "",
    val inputEnabled: Boolean = true,
    val usernameIsInvalid: Boolean = false,
    val selectedAvatar: AvatarSuggestion? = null,
    val availableAvatars: Lce<GenericError, List<AvatarSuggestion>> = Lce.Loading
) {

    val trainerId: UUID = UUID.randomUUID()
    val localTrainerId: String = trainerId.toString().uppercase()
}