package io.github.lexadiky.pdx.library.nibbler

import androidx.navigation.NavController
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.library.nibbler.android.ComposeNavigator
import io.github.lexadiky.pdx.library.nibbler.android.util.ShareIntentSender
import io.github.lexadiky.pdx.library.nibbler.decoration.DecorationViewModel

fun NibblerModule(navigator: NavController) = eagerModule("library-nibbler") {
    single<Navigator> { ComposeNavigator(navigator) }
    single<ShareIntentSender> { ShareIntentSender(inject()) }
    internal {
        singleViewModel<DecorationViewModel> {
            DecorationViewModel(inject())
        }
    }
}

