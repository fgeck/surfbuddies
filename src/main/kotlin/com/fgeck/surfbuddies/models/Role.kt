package com.fgeck.surfbuddies.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


enum class ValidRoleName {
    USER, ADMIN, UNDEFINED
}

@Document
class Role(name: ValidRoleName) {
    @Id
    @JsonIgnore
    val id: ObjectId = ObjectId.get()
    val name: ValidRoleName = name

    // many to many
//    var users: MutableSet<User>? = null
}

class CreateRoleForm {
    val name: String = ""
}

class RoleToUserForm {
    val role: String = ""
    val email: String = ""
}