package io.github.lexadiky.pdx.feature.account.login

import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.account.usecase.RegisterNewUserUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetAllPokemonPreviewsUseCase
import io.github.lexadiky.pdx.feature.account.login.util.UsernameGenerator
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.core.lce.map
import io.github.lexadiky.pdx.library.core.utils.asLce
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.UrlImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.image.placeholder
import kotlinx.coroutines.launch

internal class LoginPageSocket(
    private val usernameGenerator: UsernameGenerator,
    private val registerNewUser: RegisterNewUserUseCase,
    private val navigator: Navigator,
    private val getPokemonPreview: GetAllPokemonPreviewsUseCase,
) : ViewModelSocket<LoginPageState, LoginPageAction>(LoginPageState()) {

    init {
        viewModelScope.launch {
            state = state.copy(
                availableAvatars = getPokemonPreview.invoke().asLce()
                    .map { previews ->
                        previews.map { preview ->
                            (preview.normalSprite?.let(ImageResource::from)
                                ?: ImageResource.placeholder()) as UrlImageResource

                        }
                    }
            )
        }
    }

    override suspend fun onAction(action: LoginPageAction) {
        when (action) {
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
                registerNewUser(
                    username = state.username,
                    trainerId = state.trainerId.toString(),
                    avatarUrl = state.selectedAvatar?.url
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