package com.numpi.numpiserver.models

data class User(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
)
