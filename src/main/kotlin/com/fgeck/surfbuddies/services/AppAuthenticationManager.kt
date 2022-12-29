package com.fgeck.surfbuddies.services

import com.fgeck.surfbuddies.config.SecurityConfig
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.stereotype.Component

@Component
class AppAuthenticationProvider(private val userDetailsService: UserDetailsService) {

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(SecurityConfig().bCryptPasswordEncoder())
        return authenticationProvider
    }
}
