package com.fgeck.surfbuddies.controllers

import com.fgeck.surfbuddies.dtos.LoginRequest
import com.fgeck.surfbuddies.dtos.LoginResponse
import com.fgeck.surfbuddies.dtos.Message
import com.fgeck.surfbuddies.exceptions.BadRequestException
import com.fgeck.surfbuddies.models.*
import com.fgeck.surfbuddies.securtiy.JwtUtils
import com.fgeck.surfbuddies.services.UserDetailsService
import com.fgeck.surfbuddies.services.UserService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService,
    private val userDetailsService: UserDetailsService,
    private val authenticationProvider: AuthenticationProvider,
    private val jwtUtils: JwtUtils
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse): ResponseEntity<Any> {
        val authentication =
            authenticationProvider.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails = userDetailsService.loadUserByUsername(request.email)
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse(jwtUtils.generateToken(userDetails)))
    }

    @PostMapping("/register", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@RequestBody user: User): ResponseEntity<Any> {
        if (this.userService.userExistsByEmail(user.email)) {
            throw BadRequestException("Email address already in use") // exception, or just HTTP Response?
        }

        val result = this.userService.saveUser(user)
        val location: URI = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/me/{id}")
            .buildAndExpand(result.id).toUri()

        return ResponseEntity.created(location)
            .body(Message("User registered successfully"))
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return ResponseEntity.status(HttpStatus.OK).body(Message("success"))
    }
}
