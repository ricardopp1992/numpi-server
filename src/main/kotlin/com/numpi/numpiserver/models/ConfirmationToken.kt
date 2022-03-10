package com.numpi.numpiserver.models

import com.numpi.numpiserver.models.appuser.AppUser
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class ConfirmationToken (
    val token: String,
    val createdAt: LocalDateTime,
    val expiresAt: LocalDateTime,

    @ManyToOne
    @JoinColumn(
        name = "app_user_id"
    )
    val appUser: AppUser,
) {
    @SequenceGenerator(
        name = "confirmation_token_sequence",
        sequenceName = "confirmation_token_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "confirmation_token_sequence"
    )
    var id: Long = 0

    val confirmedAt: LocalDateTime? = null
}