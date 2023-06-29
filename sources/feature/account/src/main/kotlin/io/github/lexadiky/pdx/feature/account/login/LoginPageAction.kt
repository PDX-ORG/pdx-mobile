package io.github.lexadiky.pdx.feature.account.login

sealed interface LoginPageAction {

    object ShuffleUsername : LoginPageAction

    object Login : LoginPageAction

    data class UpdateUsername(val newUsername: String) : LoginPageAction
}