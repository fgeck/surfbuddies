package com.fgeck.surfbuddies.services


import com.fgeck.surfbuddies.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsService(private val userRepository: UserRepository) :
    UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email)?.get() ?: throw UsernameNotFoundException("email not found")
    }
}
