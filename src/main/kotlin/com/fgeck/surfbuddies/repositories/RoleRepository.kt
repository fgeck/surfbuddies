package com.fgeck.surfbuddies.repositories

import com.fgeck.surfbuddies.models.Role
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface RoleRepository: MongoRepository<Role, String> {
    fun findByName(name: String): Optional<Role>
}