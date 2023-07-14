package io.github.lexadiky.pdx.domain.account.usecase

import io.github.lexadiky.pdx.domain.account.repository.AccountRepository

class RegisterNewUserUseCase internal constructor(
    private val repository: AccountRepository,
) {

    operator suspend fun invoke(username: String, trainerId: String, avatarUrl: String?) {
        repository.createAccount(username, trainerId, avatarUrl)
    }
}
