package io.github.lexadiky.pdx.domain.account.repository

import io.github.lexadiky.pdx.domain.account.entity.UserAccount
import io.github.lexadiky.pdx.library.microdata.MicrodataManager
import io.github.lexadiky.pdx.library.microdata.serial
import kotlinx.coroutines.flow.Flow

internal class AccountRepository(
    microdataManager: MicrodataManager,
) {
    private val microdata = microdataManager.acquire(this, "account")
    private val accountRecord = microdata.serial<UserAccount>("account_record")

    suspend fun createAccount(username: String, trainerId: String, avatarUrl: String?) {
        accountRecord.set(UserAccount(username, trainerId, avatarUrl))
    }

    suspend fun account(): Flow<UserAccount?> = accountRecord.observe()

    suspend fun logout() {
        accountRecord.delete()
    }
}