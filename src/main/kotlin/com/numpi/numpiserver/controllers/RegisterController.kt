package com.numpi.numpiserver.controllers

import com.numpi.numpiserver.models.RegistrationRequest
import com.numpi.numpiserver.models.appuser.AppUser
import com.numpi.numpiserver.models.jwt.UsernamePasswordAuthenticationRequest
import com.numpi.numpiserver.services.AppUserService
import com.numpi.numpiserver.services.RegistrationService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api/v1/user")
class RegisterController (
    private val registrationService: RegistrationService,
    private val appUserService: AppUserService,
) {

    @GetMapping("/hola")
    fun getUsers(): List<AppUser> = appUserService.getUsers()

    @PostMapping("/signup")
    fun loginUser(@RequestBody registrationRequest: RegistrationRequest) =  registrationService
        .register(registrationRequest)

    @GetMapping("/confirm-token")
    fun confirmToken(@RequestParam token: String) = registrationService.confirmToken(token)

    @PostMapping("/login")
    fun login(
        @RequestBody usernamePasswordAuthenticationRequest: UsernamePasswordAuthenticationRequest,
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ) {
        println(request)
    }
}