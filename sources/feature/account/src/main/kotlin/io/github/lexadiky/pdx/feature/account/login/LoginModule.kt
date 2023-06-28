package io.github.lexadiky.pdx.feature.account.login

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.feature.account.login.util.UsernameGenerator

internal val LoginModule by module("feature-account-login") {
    internal {
        single { UsernameGenerator() }
        singleViewModel { LoginPageSocket(inject()) }
    }
}