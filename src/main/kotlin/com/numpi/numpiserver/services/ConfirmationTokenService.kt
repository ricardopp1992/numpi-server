package com.numpi.numpiserver.services

import com.numpi.numpiserver.models.ConfirmationToken
import com.numpi.numpiserver.repositories.ConfirmationTokenRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ConfirmationTokenService (
    val confirmationTokenRepository: ConfirmationTokenRepository
) {
    fun saveConfirmationTokenRepository(confirmationToken: ConfirmationToken) {
        confirmationTokenRepository.save(confirmationToken)
    }

    fun getToken(token: String): Optional<ConfirmationToken> =
        confirmationTokenRepository.findByToken(token)

    fun setConfirmedAt(token: String) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now())
    }
}