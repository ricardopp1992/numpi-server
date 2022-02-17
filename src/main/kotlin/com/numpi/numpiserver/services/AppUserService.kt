package com.numpi.numpiserver.services

import com.numpi.numpiserver.appuser.AppUser
import com.numpi.numpiserver.models.AppUserRole
import com.numpi.numpiserver.models.ConfirmationToken
import com.numpi.numpiserver.models.RegistrationRequest
import com.numpi.numpiserver.repositories.AppUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AppUserService (
    private val userRepository: AppUserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val confirmationTokenService: ConfirmationTokenService,
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails = userRepository
        .findByEmail(email)
        .orElseThrow {
            UsernameNotFoundException("User with email $email not found")
        }

    fun signUpUser(registrationRequest: RegistrationRequest): String {
        if (userRepository.findByEmail(registrationRequest.email).isPresent) {
            throw IllegalArgumentException("Email already exist")
        }

        val appUser = AppUser(
            registrationRequest.firstName,
            registrationRequest.lastName,
            registrationRequest.email,
            AppUserRole.USER,
        )

        val encodedPassword = passwordEncoder.encode(registrationRequest.password)
        appUser.password = encodedPassword

        userRepository.save(appUser)
        val token = "${UUID.randomUUID()}"
        val confirmationToken = ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            appUser
        )
        confirmationTokenService.saveConfirmationTokenRepository(confirmationToken)

        // TODO: Send email
        return token
    }

    fun enabledUser(email: String) {
        userRepository.enableAppUser(email)
    }
}
