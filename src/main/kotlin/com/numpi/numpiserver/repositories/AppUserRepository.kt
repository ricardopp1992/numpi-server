package com.numpi.numpiserver.repositories

import com.numpi.numpiserver.appuser.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(readOnly = true)
interface AppUserRepository: JpaRepository<AppUser, Long> {
    fun findByEmail(email: String): Optional<AppUser>

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.enabled = TRUE WHERE a.email = ?1")
    fun enableAppUser(email: String)
}