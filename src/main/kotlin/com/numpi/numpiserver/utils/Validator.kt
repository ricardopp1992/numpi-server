package com.numpi.numpiserver.utils

import org.springframework.stereotype.Service

@Service
class Validator {

    fun validateEmail(email: String): Boolean {
        // TODO: Validate email with regex
        return true
    }
}