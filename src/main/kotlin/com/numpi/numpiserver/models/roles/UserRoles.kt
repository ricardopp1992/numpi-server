package com.numpi.numpiserver.models.roles

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class UserRoles {
    USER,
    ADMIN;

    fun getGranderAuthorities(): MutableSet<SimpleGrantedAuthority> {
        return mutableSetOf(SimpleGrantedAuthority("ROLE_$name"))
    }
}