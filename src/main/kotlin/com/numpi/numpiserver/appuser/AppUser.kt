package com.numpi.numpiserver.appuser

import com.numpi.numpiserver.models.AppUserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class AppUser constructor(
    var firstName: String,
    var lastName: String,
    var email: String,

    @Enumerated(EnumType.STRING)
    var appUserRole: AppUserRole,
): UserDetails {

    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "user_sequence"
    )
    val id: Long = 0

    private var locked: Boolean = false
    private var enabled: Boolean = false
    private var password: String = ""

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authority = SimpleGrantedAuthority(appUserRole.name)
        return mutableListOf(authority)
    }

    fun setPassword(encodedPassword: String) {
        password = encodedPassword
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = !locked

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = enabled
}