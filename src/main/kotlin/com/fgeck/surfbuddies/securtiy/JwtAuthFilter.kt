package com.fgeck.surfbuddies.securtiy

import com.fgeck.surfbuddies.config.SecurityConfig
import com.fgeck.surfbuddies.services.UserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val userDetailsService: UserDetailsService,
    private val jwtUtils: JwtUtils
) :
    OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String = request.getHeader(AUTHORIZATION) ?: ""
        val userEmail: String
        val jwtToken: String

        if (authHeader != "" && authHeader.startsWith(SecurityConfig().tokenPrefix)) {
            jwtToken = authHeader.substring(7)
            userEmail = jwtUtils.extractUsername(jwtToken)
            if (userEmail.isNotEmpty() && (SecurityContextHolder.getContext().authentication == null)) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(userEmail)
                val isTokenValid: Boolean = jwtUtils.isTokenValid(jwtToken, userDetails)
                if (isTokenValid) {
                    val authToken: UsernamePasswordAuthenticationToken =
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        }
        filterChain.doFilter(request, response)
    }

}