package com.fgeck.surfbuddies.services

import com.fgeck.surfbuddies.models.User
import com.fgeck.surfbuddies.repositories.UserRepository
import org.bson.types.ObjectId
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


interface IUserService {
    fun saveUser(user: User): User
    fun getAllUsers(): MutableList<User?>
    fun userExistsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
    fun findById(id: ObjectId): User?
}

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) :
    IUserService {

    override fun saveUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return this.userRepository.save(user)
    }

    override fun getAllUsers(): MutableList<User?> {
        return this.userRepository.findAll()
    }

    override fun userExistsByEmail(email: String): Boolean {
        return this.userRepository.existsByEmail(email) == true
    }

    override fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email) ?: throw UsernameNotFoundException("email not found")
    }

    override fun findById(id: ObjectId): User? {
        return this.userRepository.findById(id) ?: throw UsernameNotFoundException("id not found")
    }

}