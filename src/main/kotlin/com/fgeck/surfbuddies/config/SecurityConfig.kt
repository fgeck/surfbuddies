package com.fgeck.surfbuddies.config

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.annotation.Validated

@Configuration
@ConfigurationProperties(prefix = "jwt-security")
@Validated
class SecurityConfig {
    @NotBlank
    @Size(min = 20)
    var secret = ""

    @Positive
    var expirationTimeInDays: Int = 31 // in days

    @Positive
    var strength = 10

    // constants
    val tokenPrefix = "Bearer "
    val headerString = "Authorization"

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder(this.strength)
}
