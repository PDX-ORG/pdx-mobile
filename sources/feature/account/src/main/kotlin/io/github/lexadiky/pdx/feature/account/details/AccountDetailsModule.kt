package io.github.lexadiky.pdx.feature.account.details

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.pdx.domain.account.AccountDomainModule

val AccountDetailsModule by module("feature-account-details") {
    import(AccountDomainModule)
    internal {
        singleViewModel { AccountDetailsSocket(inject(), inject(), inject()) }
    }
}