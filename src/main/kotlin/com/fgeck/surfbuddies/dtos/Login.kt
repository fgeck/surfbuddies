package com.fgeck.surfbuddies.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest(@Email val email: String, @NotBlank val password: String)
data class LoginResponse(val jwtToken: String)