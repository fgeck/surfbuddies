package com.fgeck.surfbuddies.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

enum class UserGroup {
    TEACHER, SCHOOL
}

@Document
class User(@NotBlank password: String, @Email var email: String) {
    @Id
    var id: ObjectId = ObjectId.get()
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String = BCryptPasswordEncoder().encode(password) // make that nicer!
    var firstName: String = ""
    var lastName: String = ""
    var userGroup: UserGroup = UserGroup.TEACHER
    // many to many
    var roles: MutableSet<Role> = mutableSetOf<Role>()

    fun passwordIsEqualTo(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}
