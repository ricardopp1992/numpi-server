package com.numpi.numpiserver.models.appuser

import com.numpi.numpiserver.models.roles.UserRoles
import javax.persistence.*

@Entity
class AppUser constructor(
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,

    var appUserRole: UserRoles,
) {

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

    var locked: Boolean = false
    var enabled: Boolean = true

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    var authProvider: AuthenticationProvider? = null
}