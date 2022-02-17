package com.numpi.numpiserver.services

import com.numpi.numpiserver.models.RegistrationRequest
import com.numpi.numpiserver.utils.Validator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RegistrationService (
    private val appUserService: AppUserService,
    private val validator: Validator,
    private val confirmationTokenService: ConfirmationTokenService,
) {

    fun register(registrationRequest: RegistrationRequest): String {
        if (!validator.validateEmail(registrationRequest.email)) {
            throw IllegalArgumentException("Email not valid")
        }

        return appUserService.signUpUser(registrationRequest)
    }

    fun confirmToken(token: String): String {
        val now = LocalDateTime.now()
        val confirmationToken = confirmationTokenService
            .getToken(token)
            .orElseThrow { throw IllegalStateException("Token not found") }

        if (confirmationToken.confirmedAt != null) {
            throw IllegalStateException("Token already confirm ")
        }

        if (confirmationToken.expiresAt.isBefore(now)) {
            throw IllegalStateException("The token has expired")
        }

        confirmationTokenService.setConfirmedAt(token)
        appUserService.enabledUser(confirmationToken.appUser.email)
        return "Confirmed!"
    }

}
