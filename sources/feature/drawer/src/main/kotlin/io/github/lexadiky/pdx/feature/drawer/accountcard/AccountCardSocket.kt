package io.github.lexadiky.pdx.feature.drawer.accountcard

import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.account.usecase.SubscribeToUserAccountUseCase
import io.github.lexadiky.pdx.feature.drawer.R
import io.github.lexadiky.pdx.feature.drawer.accountcard.AccountCardState.Companion.DEFAULT_AVATAR
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class AccountCardSocket(
    private val navigator: Navigator,
    private val subscribeToUserAccount: SubscribeToUserAccountUseCase
) : ViewModelSocket<AccountCardState, AccountCardAction>(AccountCardState()) {

    init {
        viewModelScope.launch {
            subscribeToUserAccount().collectLatest { account ->
                state = state.copy(
                    username = account?.username?.let { StringResource.from(it) }
                        ?: StringResource.from(R.string.drawer_account_card_username_default),
                    avatar = account?.avatarUrl?.let(ImageResource::from)
                        ?: DEFAULT_AVATAR,
                    isLoggedIn = account != null
                )
            }
        }
    }

    override suspend fun onAction(action: AccountCardAction) {
        when (action) {
            AccountCardAction.LoginClicked -> onLoginClicked()
            AccountCardAction.AccountClicked -> onAccountClicked()
        }
    }

    private suspend fun onLoginClicked() {
        navigator.navigate("pdx://account/login")
    }

    private suspend fun onAccountClicked() {
        navigator.navigate("pdx://account")
    }
}
