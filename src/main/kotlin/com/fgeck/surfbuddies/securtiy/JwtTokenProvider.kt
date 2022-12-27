//package com.fgeck.surfbuddies.securtiy
//
//import com.fgeck.surfbuddies.config.SecurityConfig
//import com.fgeck.surfbuddies.services.UserDetailsService
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.SignatureAlgorithm
//import io.jsonwebtoken.security.Keys
//import jakarta.annotation.PostConstruct
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.userdetails.User
//import org.springframework.stereotype.Component
//import java.security.Key
//import java.util.*
//
//
//@Component
//class JwtTokenProvider(
//    private val securityConfig: SecurityConfig,
//    private val userDetailsService: UserDetailsService,
//) {
//    private var key: Key? = null
//
//    @PostConstruct
//    fun init() {
//        key = Keys.hmacShaKeyFor(securityConfig.secret.toByteArray())
//    }
//
//    fun createToken(authentication: Authentication): String {
//        val authClaims: MutableList<String> = mutableListOf()
//        authentication.authorities?.let { authorities ->
//            authorities.forEach { claim -> authClaims.add(claim.toString()) }
//        }
//        val dt = Date()
//        val c = Calendar.getInstance()
//        c.time = dt
//        c.add(Calendar.DATE, securityConfig.expirationTimeInDays)
//        val tokenValidity = c.time
//        return Jwts.builder()
//            .setSubject(authentication.name)
//            .claim("auth", authClaims)
//            .setExpiration(tokenValidity)
//            .signWith(key, SignatureAlgorithm.HS512)
//            .compact()
//    }
//
//    fun getAuthentication(token: String): Authentication? {
//        return try {
//            val claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token.replace(securityConfig.tokenPrefix, ""))
//            val userDetail = userDetailsService.loadUserByUsername(claims.body.subject)
//            val principal = User(userDetail.username, "", userDetail.authorities)
//            UsernamePasswordAuthenticationToken(principal, token, userDetail.authorities)
//        } catch (e: Exception) {
//            return null
//        }
//    }
//}