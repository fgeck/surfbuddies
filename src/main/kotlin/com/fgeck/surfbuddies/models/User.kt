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

enum class Sport(name: String) {
    UNKNOWN("UNKNOWN"),
    KITESURFING("KITESURFING"),
    KITEFOILING("KITEFOILING"),
    WINGFOILING("WINGFOILING"),
    WINDSURFING("WINDSURFING"),
    WAVERIDING("WAVERIDING"),
    STANDUPPADDLING("STANDUPPADDLING")
}

@Document
data class User(
    @NotBlank @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) var password: String,
    @Email var email: String,
    @Id
    var id: ObjectId = ObjectId.get(),
    var firstname: String = "",
    var lastname: String = "",
    var userGroup: UserGroup = UserGroup.TEACHER,
    var sports: MutableSet<Sport> = mutableSetOf(),
    var profilePicturePath: String = "",
    
    var roles: Role = Role.USER,
)
