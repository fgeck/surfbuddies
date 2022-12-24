package com.fgeck.surfbuddies.services


import com.fgeck.surfbuddies.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AppUserDetailsService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = this.userRepository.findByEmail(email)?.get() ?: throw UsernameNotFoundException("email not found")
        val authorities = ArrayList<GrantedAuthority>()
        user.roles.forEach { authorities.add(SimpleGrantedAuthority(it.name.toString())) }

        return User(
            user.email,
            user.password,
            authorities
        )
    }
}
