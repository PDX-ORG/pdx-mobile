package io.github.lexadiky.pdx.feature.account.login

import io.github.lexadiky.pdx.feature.account.login.util.UsernameGenerator
import io.github.lexadiky.pdx.library.arc.ViewModelSocket

internal class LoginPageSocket(
    private val usernameGenerator: UsernameGenerator,
) : ViewModelSocket<LoginPageState, LoginPageAction>(LoginPageState()) {

    override suspend fun onAction(action: LoginPageAction) {
        when(action) {
            is LoginPageAction.UpdateUsername -> updateUsername(action)
            is LoginPageAction.ShuffleUsername -> shuffleUsername()
        }
    }

    private fun shuffleUsername() {
        updateUsername(LoginPageAction.UpdateUsername(usernameGenerator.next()))
    }

    private fun updateUsername(action: LoginPageAction.UpdateUsername) {
        state = state.copy(username = action.newUsername)
    }
}