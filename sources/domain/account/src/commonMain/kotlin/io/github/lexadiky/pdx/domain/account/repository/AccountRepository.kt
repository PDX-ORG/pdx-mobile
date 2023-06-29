package io.github.lexadiky.pdx.domain.account.repository

import io.github.lexadiky.pdx.domain.account.entity.UserAccount
import io.github.lexadiky.pdx.library.microdata.MicrodataManager
import io.github.lexadiky.pdx.library.microdata.zip
import java.util.UUID
import kotlinx.coroutines.flow.Flow

// TODO rework it
internal class AccountRepository(
    private val microdataManager: MicrodataManager
) {
    private val microdata = microdataManager.acquire(this, "account")

    private val usernameMd = microdata.string("username")
    private val trainerIdMd = microdata.string("trainer_id")

    suspend fun createAccount(username: String, trainerId: UUID) {
        usernameMd.set(username)
        trainerIdMd.set(trainerId.toString())
    }

    suspend fun account(): Flow<UserAccount?> = microdataManager.zip(usernameMd, trainerIdMd) { username, trainerId ->
        UserAccount(username, UUID.fromString(trainerId))
    }
}