package com.fgeck.surfbuddies.services

import com.fgeck.surfbuddies.config.SecurityConfig
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.stereotype.Component

//
//import org.springframework.context.annotation.Bean
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.stereotype.Component
//
//
//@Component
//class AppAuthenticationManager(
//    private val userDetailsService: UserDetailsService, val bCryptPasswordEncoder: BCryptPasswordEncoder,
//) : AuthenticationManager {
//
//        @Throws(AuthenticationException::class)
//    override fun authenticate(authentication: Authentication): Authentication? {
//        val password = authentication.credentials.toString()
//        val user = userDetailsService.loadUserByUsername(authentication.name)
//        if (!bCryptPasswordEncoder.matches(password, user.password)) {
//            throw BadCredentialsException("Bad credentials")
//        }
//        return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
//    }
//
//}
//
//


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