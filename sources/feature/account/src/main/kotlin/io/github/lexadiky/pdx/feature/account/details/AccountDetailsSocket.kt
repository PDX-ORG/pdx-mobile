package io.github.lexadiky.pdx.feature.account.details

import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.account.usecase.LogoutUseCase
import io.github.lexadiky.pdx.domain.account.usecase.SubscribeToUserAccountUseCase
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.nibbler.Navigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class AccountDetailsSocket(
    private val logoutUseCase: LogoutUseCase,
    private val subscribeToUserAccountUseCase: SubscribeToUserAccountUseCase,
    private val navigator: Navigator
) : ViewModelSocket<AccountDetailsState, AccountDetailsAction>(AccountDetailsState()) {

    init {
        viewModelScope.launch {
            subscribeToUserAccountUseCase().collectLatest { account ->
                account?.let {
                    state = state.copy(
                        username = account.username,
                        localTrainerId = account.localTrainerId
                    )
                }
            }
        }
    }

    override suspend fun onAction(action: AccountDetailsAction) {
        when (action) {
            AccountDetailsAction.OnLogoutClicked -> logout()
        }
    }

    private suspend fun logout() {
        logoutUseCase()
        navigator.back()
    }
}