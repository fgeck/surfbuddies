package com.fgeck.surfbuddies.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

enum class UserGroup {
    TEACHER, SCHOOL
}

@Document
data class User(
    @NotBlank @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) var password: String,
    @Email var email: String
) {
    @Id
    var id: ObjectId = ObjectId.get()
    var firstName: String = ""
    var lastName: String = ""
    var userGroup: UserGroup = UserGroup.TEACHER

    // many to many
    var roles: MutableSet<Role> = mutableSetOf<Role>()
}
