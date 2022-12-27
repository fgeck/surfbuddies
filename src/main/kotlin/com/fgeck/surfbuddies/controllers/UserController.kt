package com.fgeck.surfbuddies.controllers

import com.fgeck.surfbuddies.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    fun getAllUSers(): ResponseEntity<Any> {
        val users = this.userService.getAllUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }

//    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
//        if (jwt == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message("unauthorized"))
//        }
//        val algorithm: Algorithm = Algorithm.HMAC256("secret") //use more secure key
//        val verifier: JWTVerifier = JWT.require(algorithm)
//            .withIssuer("1")
//            .build() //Reusable verifier instance
//
//        val decodedJwt: DecodedJWT = verifier.verify(jwt)
//        val u = this.userService.findById(decodedJwt.issuer.toInt())
//        return ResponseEntity.status(HttpStatus.OK).body(Message(decodedJwt.toString()))
//    }


}