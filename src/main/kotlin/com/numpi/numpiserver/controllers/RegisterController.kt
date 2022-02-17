package com.numpi.numpiserver.controllers

import com.numpi.numpiserver.models.RegistrationRequest
import com.numpi.numpiserver.services.RegistrationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class RegisterController (private val registrationService: RegistrationService) {

    @PostMapping("/signup")
    fun loginUser(@RequestBody registrationRequest: RegistrationRequest) =  registrationService
        .register(registrationRequest)

    @GetMapping("/confirm-token")
    fun confirmToken(@RequestParam token: String) = registrationService.confirmToken(token)
}