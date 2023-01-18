package com.fgeck.surfbuddies.securtiy

import com.fgeck.surfbuddies.config.SecurityConfig
import com.fgeck.surfbuddies.models.User
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

    fun generateToken(user: User): String {
        val claims: Map<String, Any> = HashMap<String, Any>()
        return createToken(claims, user)
    }

    fun generateToken(user: User, claims: Map<String, Any>): String {
        return createToken(claims, user)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username: String = extractEmail(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    fun extractEmail(token: String): String {
        val claims: Claims = extractAllClaims(token)
        return claims.get("email", String::class.java) ?: ""
    }

    fun isTokenExpired(token: String): Boolean {
        val claims: Claims = extractAllClaims(token)
        return claims.expiration.before(Date())
    }

    private fun createToken(claims: Map<String, Any>, user: User): String {
        val authClaims: MutableList<String> = mutableListOf()

        val dateNow = Date()
        val c = Calendar.getInstance()
        c.time = dateNow
        c.add(Calendar.DATE, securityProperties.expirationTimeInDays)
        val validUntil = c.time
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.id.toString())
            .claim("given_name", user.firstname)
            .claim("family_name", user.lastname)
            .claim("email", user.email)
            .setIssuedAt(dateNow)
            .setExpiration(validUntil)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }
}