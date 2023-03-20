package com.fgeck.surfbuddies

import com.fgeck.surfbuddies.dtos.LoginRequest
import com.fgeck.surfbuddies.dtos.LoginResponse
import com.fgeck.surfbuddies.dtos.Message
import com.fgeck.surfbuddies.models.Role
import com.fgeck.surfbuddies.models.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(
    classes = arrayOf(SurfbuddiesApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class SurfbuddiesApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @Test
    fun registerUserOnlyOnce() {
        var result = restTemplate.postForEntity(
            "/api/auth/register",
            User(email = "testcase1@test.com", password = "ab12cd34", roles = mutableSetOf(Role.USER)),
            Message::class.java
        )
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertNotNull(result.headers.location)
        assertEquals("user registered successfully", result.body?.message)

        result = restTemplate.postForEntity(
            "/api/auth/register",
            User(email = "testcase1@test.com", password = "ab12cd34", roles = mutableSetOf(Role.USER)),
            Message::class.java
        )
        assertEquals(HttpStatus.CONFLICT, result.statusCode)
        assertEquals("user already exists", result.body?.message)
        mongoTemplate.db.drop()
    }

    @Test
    fun loginUser() {
        val email = "testcase2@test.com"
        val password = "ab12cd34"
        val result = restTemplate.postForEntity(
            "/api/auth/register",
            User(email = email, password = password, roles = mutableSetOf(Role.USER)),
            Message::class.java
        )
        assertEquals(HttpStatus.CREATED, result.statusCode)

        val jwt = restTemplate.postForEntity(
            "/api/auth/login",
            LoginRequest(email, password),
            LoginResponse::class.java
        )

        assertNotNull(jwt)
    }
}
