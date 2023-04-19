package io.github.lexadiky.pdx.lib.navigation

import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.akore.lechuck.robo.ComposeNavigationContext

fun NavigationModule(context: ComposeNavigationContext) = eagerModule("library-navigation") {
    single { context.navGraph }
    single { context.navigator }
    single { context.controller }
    single { context.decorationController }
    single { ShareIntentSender(inject()) }
}
