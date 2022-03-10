package com.numpi.numpiserver.models.jwt

data class UsernamePasswordAuthenticationRequest (
    val username: String,
    val password: String,
) {
}