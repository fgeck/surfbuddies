package com.fgeck.surfbuddies.repositories

import com.fgeck.surfbuddies.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface UserRepository : MongoRepository<User?, String?> {
    fun findByEmail(email: String?): Optional<User?>?
    fun existsByEmail(email: String?): Boolean?
    fun findById(id: ObjectId): Optional<User?>
}