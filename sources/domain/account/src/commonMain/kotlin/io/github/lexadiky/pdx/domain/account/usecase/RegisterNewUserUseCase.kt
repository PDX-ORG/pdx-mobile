package io.github.lexadiky.pdx.domain.account.usecase

import io.github.lexadiky.pdx.domain.account.repository.AccountRepository
import java.util.UUID

class RegisterNewUserUseCase internal constructor(
    private val repository: AccountRepository
) {

    operator suspend fun invoke(username: String, trainerId: UUID) {
        repository.createAccount(username, trainerId)
    }
}
