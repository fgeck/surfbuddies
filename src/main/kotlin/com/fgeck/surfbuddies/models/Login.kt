package com.fgeck.surfbuddies.models

import jakarta.validation.constraints.NotBlank

class Login {
    @NotBlank
    val email: String = ""
    @NotBlank
    val password: String = ""
}