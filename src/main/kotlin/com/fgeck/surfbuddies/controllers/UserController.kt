package com.fgeck.surfbuddies.controllers

import com.fgeck.surfbuddies.dtos.UpdateUserRequest
import com.fgeck.surfbuddies.services.UserService
import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAllUSers(): ResponseEntity<Any> {
        val users = this.userService.getAllUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable("userId") userId: ObjectId): ResponseEntity<Any> {
        val currentUser =
            userService.findById(userId) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.status(HttpStatus.OK).body(currentUser)
    }

    @PutMapping("/users/{userId}")
    fun updateUser(
        @PathVariable("userId") userId: ObjectId,
        @RequestBody @Valid updateUserRequest: UpdateUserRequest
    ): ResponseEntity<Any> {
        val currentUser =
            userService.findById(userId) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        updateUserRequest.updateUser(currentUser)
        val updatedUser = userService.saveUser(currentUser)
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser)
    }
}