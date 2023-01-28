package io.github.lexadiky.pdx.lib.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.pdx.generated.analytics.NavigationEventsSpec
import io.github.lexadiky.pdx.lib.navigation.decoration.DecorationController

internal fun NavigationModule(controller: NavHostController, navGraph: NavGraph) = eagerModule("navigation") {
    single { controller }
    single { navGraph }
    single { Navigator(inject(), inject(), inject(), inject()) }
    single { DecorationController(inject()) }

    internal {
        single { NavigationEventsSpec(inject()) }
    }
}
