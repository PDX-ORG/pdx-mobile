package io.github.lexadiky.pdx.feature.account.login

import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.account.usecase.RegisterNewUserUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetBasicPokemonPreviews
import io.github.lexadiky.pdx.feature.account.login.entity.AvatarSuggestion
import io.github.lexadiky.pdx.feature.account.login.util.UsernameGenerator
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.core.lce.map
import io.github.lexadiky.pdx.library.core.utils.asLce
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import kotlinx.coroutines.launch

internal class LoginPageSocket(
    private val usernameGenerator: UsernameGenerator,
    private val registerNewUser: RegisterNewUserUseCase,
    private val navigator: Navigator,
    private val getPokemonPreview: GetBasicPokemonPreviews,
) : ViewModelSocket<LoginPageState, LoginPageAction>(LoginPageState()) {

    init {
        viewModelScope.launch {
            state = state.copy(
                availableAvatars = getPokemonPreview.invoke().asLce()
                    .map { previews ->
                        previews
                            .mapNotNull { preview -> preview.normalSprite }
                            .map { AvatarSuggestion(ImageResource.from(it), it) }
                    }
            )
        }
    }

    override suspend fun onAction(action: LoginPageAction) {
        when (action) {
            is LoginPageAction.UpdateUsername -> updateUsername(action)
            is LoginPageAction.ShuffleUsername -> shuffleUsername()
            LoginPageAction.Login -> createAccount()
            is LoginPageAction.UpdateAvatar -> updateAvatar(action.suggestion)
        }
    }

    private fun updateAvatar(suggestion: AvatarSuggestion) {
        state = state.copy(selectedAvatar = suggestion)
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
                registerNewUser(
                    username = state.username,
                    trainerId = state.trainerId.toString(),
                    avatarUrl = state.selectedAvatar?.uri
                )
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