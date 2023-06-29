package io.github.lexadiky.pdx.library.nibbler.android

import androidx.navigation.NavController
import io.github.lexadiky.pdx.library.nibbler.NavigateCommand
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.Route
import kotlinx.coroutines.flow.MutableStateFlow

class ComposeNavigator(
    private val composeNavigator: NavController
) : Navigator {

    override val navigateCommand: MutableStateFlow<NavigateCommand> = MutableStateFlow(NavigateCommand.GoTo(Route.INDEX))

    override suspend fun navigate(route: Route) {
        navigateCommand.value = NavigateCommand.GoTo(route)
    }

    override suspend fun back() {
        navigateCommand.value = NavigateCommand.Back()
    }
}
