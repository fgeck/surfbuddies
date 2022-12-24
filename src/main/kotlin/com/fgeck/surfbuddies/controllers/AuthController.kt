package com.fgeck.surfbuddies.controllers

import com.fgeck.surfbuddies.dtos.Message
import com.fgeck.surfbuddies.exceptions.BadRequestException
import com.fgeck.surfbuddies.models.*
import com.fgeck.surfbuddies.services.UserService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("auth")
class AuthController(@Autowired private val userService: UserService) {

//    @PostMapping("login")
//    fun login(@Valid @RequestBody login: Login, response: HttpServletResponse): ResponseEntity<Any> {
//        val user = this.userService.findByEmail(email = login.email)
//            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message("not found"))
//        if(!user.passwordIsEqualTo(login.password)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message("invalid password"))
//        }
//        val algorithm: Algorithm = Algorithm.HMAC256("secret")
//        val token = JWT.create()
//            .withIssuer(user.id.toString()) // WithexpiresAt needed?
//            .sign(algorithm)
//        val cookie = Cookie("jwt", token)
//        cookie.isHttpOnly = true
//
//        response.addCookie(cookie)
//
//        return ResponseEntity.status(HttpStatus.OK).body(Message("success"))
//    }

    @PostMapping("register", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@Valid @RequestBody user: User): ResponseEntity<Any> {
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

    @PostMapping("roles")
    fun saveRole(@Valid @RequestBody body: CreateRoleForm): ResponseEntity<Any> {
        val result = this.userService.saveRole(Role(name= ValidRoleName.valueOf(body.name)))
        val location: URI = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/roles/{id}")
            .buildAndExpand(result.id).toUri()

        return ResponseEntity.created(location)
            .body(Message("User registered successfully"))
    }

    @PostMapping("roles/addtouser")
    fun addRoleToUser(@Valid @RequestBody roleToUserForm: RoleToUserForm): ResponseEntity<Any> {
//        this.userService.addRoleToUser(roleToUserForm.email, ValidRoleName.valueOf(roleToUserForm.role))
        return ResponseEntity.ok().build()
    }
}
