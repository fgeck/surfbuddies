package com.fgeck.surfbuddies.securtiy

import com.fgeck.surfbuddies.config.SecurityConfig
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException

class JwtAuthorizationFilter(
    authManager: AuthenticationManager,
    private val securityProperties: SecurityConfig,
    private val tokenProvider: JwtTokenProvider

) : BasicAuthenticationFilter(authManager) {


    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = req.getHeader(securityProperties.headerString)
        if (header == null || !header.startsWith(securityProperties.tokenPrefix)) {
            chain.doFilter(req, res)
            return
        }
        tokenProvider.getAuthentication(header)?.also { authentication: Authentication ->
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(req, res)
    }
}