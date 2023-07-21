package io.github.lexadiky.pdx.domain.account.repository

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.pdx.domain.account.entity.UserAccount
import io.github.lexadiky.pdx.library.microdata.EditableMicrodata
import io.github.lexadiky.pdx.library.microdata.MicrodataManager
import io.github.lexadiky.pdx.library.microdata.serial
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

// TODO fix global state
private lateinit var accountRecord: EditableMicrodata<UserAccount>

internal class AccountRepository(
    microdataManager: MicrodataManager,
) {
    private val logger = BLogger.tag("AccountRepository")

    private val microdata = microdataManager.acquire(this, "account")

    init {
        accountRecord = microdata.serial<UserAccount>("account_record")
    }

    suspend fun createAccount(username: String, trainerId: String, avatarUrl: String?) {
        accountRecord.set(UserAccount(username, trainerId, avatarUrl))
    }

    suspend fun account(): Flow<UserAccount?> = accountRecord.observe()
        .onEach { logger.info("account updated: $it") }

    suspend fun logout() {
        accountRecord.delete()
    }
}