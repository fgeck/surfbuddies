package com.fgeck.surfbuddies.securtiy

import com.fgeck.surfbuddies.config.SecurityConfig
import com.fgeck.surfbuddies.services.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtils(private val userDetailsService: UserDetailsService, private val securityProperties: SecurityConfig) {

    private var key: Key? = null

    @PostConstruct
    fun init() {
        key = Keys.hmacShaKeyFor(securityProperties.secret.toByteArray())
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap<String, Any>()
        return createToken(claims, userDetails)
    }

    fun generateToken(userDetails: UserDetails, claims: Map<String, Any>): String {
        return createToken(claims, userDetails)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username: String = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    fun extractUsername(token: String): String {
        val claims: Claims = extractAllClaims(token)
        return claims.subject
    }

    fun isTokenExpired(token: String): Boolean {
        val claims: Claims = extractAllClaims(token)
        return claims.expiration.before(Date())
    }

    private fun createToken(claims: Map<String, Any>, userDetails: UserDetails): String {
        val authClaims: MutableList<String> = mutableListOf()

        val dateNow = Date()
        val c = Calendar.getInstance()
        c.time = dateNow
        c.add(Calendar.DATE, securityProperties.expirationTimeInDays)
        val validUntil = c.time
        return Jwts.builder().setClaims(claims)
            .setSubject(userDetails.username)
            .claim("auth", authClaims)
            .setIssuedAt(dateNow)
            .setExpiration(validUntil)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }
}