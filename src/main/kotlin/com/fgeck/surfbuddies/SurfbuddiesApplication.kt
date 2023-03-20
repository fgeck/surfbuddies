package com.fgeck.surfbuddies

import com.fgeck.surfbuddies.services.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SurfbuddiesApplication {

    @Bean
    fun init(userService: UserService) = CommandLineRunner {
//        userService.saveUser(User(password = "1234", email = "f@g.de"))
//        userService.saveUser(User(password = "1234", email = "user@eins.de"))
    }
}

fun main(args: Array<String>) {
    runApplication<SurfbuddiesApplication>(*args)
}