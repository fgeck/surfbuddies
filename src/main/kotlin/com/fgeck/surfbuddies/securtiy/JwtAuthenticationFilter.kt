//package com.fgeck.surfbuddies.securtiy
//
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import com.fgeck.surfbuddies.models.User
//import com.fgeck.surfbuddies.config.SecurityConfig
//import jakarta.servlet.FilterChain
//import jakarta.servlet.ServletException
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.AuthenticationServiceException
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.AuthenticationException
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//import java.io.IOException
//
//class JwtAuthenticationFilter(
//    private val authManager: AuthenticationManager,
//    private val securityProperties: SecurityConfig,
//    private val tokenProvider: JwtTokenProvider
//) : UsernamePasswordAuthenticationFilter() {
//
//    @Throws(AuthenticationException::class)
//    override fun attemptAuthentication(
//        req: HttpServletRequest,
//        res: HttpServletResponse?
//    ): Authentication {
//        return try {
//            val mapper = jacksonObjectMapper()
//
//            val credentials = mapper
//                .readValue<User>(req.inputStream)
//
//            authManager.authenticate(
//                UsernamePasswordAuthenticationToken(
//                    credentials.email,
//                    credentials.password,
//                    ArrayList()
//                )
//            )
//        } catch (e: IOException) {
//            throw AuthenticationServiceException(e.message)
//        }
//    }
//
//    @Throws(IOException::class, ServletException::class)
//    override fun successfulAuthentication(
//        req: HttpServletRequest,
//        res: HttpServletResponse,
//        chain: FilterChain?,
//        authentication: Authentication
//    ) {
//        val token = tokenProvider.createToken(authentication)
//        res.addHeader(securityProperties.headerString, securityProperties.tokenPrefix + token)
//    }
//}