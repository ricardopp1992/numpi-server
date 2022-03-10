package com.numpi.numpiserver.controllers

import com.numpi.numpiserver.models.onboard.OnboardData
import com.numpi.numpiserver.services.OnboardService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/onboard")
class OnboardController (
    private val onboardService: OnboardService
) {

    @GetMapping
    fun getOnboardData(): List<OnboardData> = onboardService.getOnboardData()
}