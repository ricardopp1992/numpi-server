package com.numpi.numpiserver.services

import com.numpi.numpiserver.models.onboard.OnboardData
import com.numpi.numpiserver.repositories.OnboardDataRepository
import org.springframework.stereotype.Service

@Service
class OnboardService (
    private val onboardDataRepository: OnboardDataRepository
) {
    fun getOnboardData(): List<OnboardData> = onboardDataRepository.findAll()
}
