package com.numpi.numpiserver.repositories

import com.numpi.numpiserver.models.onboard.OnboardData
import org.springframework.data.jpa.repository.JpaRepository

interface OnboardDataRepository: JpaRepository<OnboardData, Long> {
}