package io.github.lexadiky.pdx.domain.account.usecase

import io.github.lexadiky.pdx.domain.account.entity.UserAccount
import io.github.lexadiky.pdx.domain.account.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class SubscribeToUserAccountUseCase internal constructor(
    private val repository: AccountRepository
) {

    operator suspend fun invoke(): Flow<UserAccount?> {
        return repository.account()
    }
}