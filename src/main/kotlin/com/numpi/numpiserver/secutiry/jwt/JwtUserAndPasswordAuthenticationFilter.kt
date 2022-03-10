package com.numpi.numpiserver.secutiry.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.numpi.numpiserver.models.jwt.UsernamePasswordAuthenticationRequest
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.time.LocalDate
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtUserAndPasswordAuthenticationFilter(
    private val authManager: AuthenticationManager,
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val authenticationRequest: UsernamePasswordAuthenticationRequest = jacksonObjectMapper()
                .readValue(request.inputStream, UsernamePasswordAuthenticationRequest::class.java)

            val authToken =  UsernamePasswordAuthenticationToken(
                authenticationRequest.username, authenticationRequest.password
            )
            return authManager.authenticate(authToken)
        } catch (err: IOException) {
            throw RuntimeException(err)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val token = Jwts.builder()
            .setSubject(authResult!!.name)
            .claim("authorities", authResult.authorities)
            .setIssuedAt(Date())
            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
            .signWith(Keys.hmacShaKeyFor("asdasdasdasqweqweacdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdffsdfasfasd".toByteArray()))
            .compact()

        response?.addHeader("Authorization", "Bearer $token")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        println("NO Sr")
    }
}