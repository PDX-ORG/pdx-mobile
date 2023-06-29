package io.github.lexadiky.pdx.feature.account.login

import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.account.usecase.RegisterNewUserUseCase
import io.github.lexadiky.pdx.feature.account.login.util.UsernameGenerator
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.nibbler.Navigator
import kotlinx.coroutines.launch

internal class LoginPageSocket(
    private val usernameGenerator: UsernameGenerator,
    private val registerNewUser: RegisterNewUserUseCase,
    private val navigator: Navigator
) : ViewModelSocket<LoginPageState, LoginPageAction>(LoginPageState()) {

    override suspend fun onAction(action: LoginPageAction) {
        when(action) {
            is LoginPageAction.UpdateUsername -> updateUsername(action)
            is LoginPageAction.ShuffleUsername -> shuffleUsername()
            LoginPageAction.Login -> createAccount()
        }
    }

    private fun shuffleUsername() {
        updateUsername(LoginPageAction.UpdateUsername(usernameGenerator.next()))
    }

    private fun updateUsername(action: LoginPageAction.UpdateUsername) {
        state = state.copy(
            username = action.newUsername,
            usernameIsInvalid = false
        )
    }

    private fun createAccount() {
        state = state.copy(inputEnabled = false)
        if (state.username.isNotBlank()) {
            viewModelScope.launch {
                registerNewUser(state.username, state.trainerId)
                navigator.back()
            }
        } else {
            state = state.copy(
                usernameIsInvalid = true,
                inputEnabled = true
            )
        }
    }
}