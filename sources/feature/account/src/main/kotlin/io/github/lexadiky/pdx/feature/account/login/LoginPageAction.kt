package io.github.lexadiky.pdx.feature.account.login

import io.github.lexadiky.pdx.feature.account.login.entity.AvatarSuggestion

internal sealed interface LoginPageAction {

    object ShuffleUsername : LoginPageAction

    object Login : LoginPageAction

    data class UpdateUsername(val newUsername: String) : LoginPageAction
    data class UpdateAvatar(val suggestion: AvatarSuggestion) : LoginPageAction
}