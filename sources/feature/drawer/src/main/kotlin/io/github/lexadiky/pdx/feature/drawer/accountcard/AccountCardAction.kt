package io.github.lexadiky.pdx.feature.drawer.accountcard

sealed interface AccountCardAction {

    object LoginClicked : AccountCardAction
    object AccountClicked : AccountCardAction
}