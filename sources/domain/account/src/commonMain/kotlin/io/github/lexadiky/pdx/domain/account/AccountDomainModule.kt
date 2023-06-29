package io.github.lexadiky.pdx.domain.account

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.account.repository.AccountRepository
import io.github.lexadiky.pdx.domain.account.usecase.RegisterNewUserUseCase
import io.github.lexadiky.pdx.domain.account.usecase.SubscribeToUserAccountUseCase

val AccountDomainModule by module("domain-account") {
    single { SubscribeToUserAccountUseCase(inject()) }
    single { RegisterNewUserUseCase(inject()) }
    internal {
        single { AccountRepository(inject()) }
    }
}
