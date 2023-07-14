package io.github.lexadiky.pdx.feature.account.details

internal sealed interface AccountDetailsAction {

    object OnLogoutClicked : AccountDetailsAction
}