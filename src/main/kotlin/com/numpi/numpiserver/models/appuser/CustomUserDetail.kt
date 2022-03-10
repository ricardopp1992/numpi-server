package com.numpi.numpiserver.models.appuser

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetail (private val appUser: AppUser): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        appUser.appUserRole.getGranderAuthorities()

    override fun getPassword(): String = appUser.password

    override fun getUsername(): String = appUser.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = !appUser.locked

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = appUser.enabled
}