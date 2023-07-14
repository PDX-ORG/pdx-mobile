package io.github.lexadiky.pdx.domain.account.usecase

import io.github.lexadiky.pdx.domain.account.repository.AccountRepository

class LogoutUseCase internal constructor(private val repository: AccountRepository) {

    operator suspend fun invoke() {
        repository.logout()
    }
}
