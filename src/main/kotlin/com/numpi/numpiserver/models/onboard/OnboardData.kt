package com.numpi.numpiserver.models.onboard

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class OnboardData(
    val textOne: String,
    val textTwo: String,
    val imageName: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
