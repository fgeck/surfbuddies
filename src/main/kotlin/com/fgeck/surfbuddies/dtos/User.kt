package com.fgeck.surfbuddies.dtos

import com.fgeck.surfbuddies.models.Sport
import com.fgeck.surfbuddies.models.User
import com.fgeck.surfbuddies.models.UserGroup

data class UpdateUserRequest(
    var email: String = "",
    var password: String = "",
    var firstname: String = "",
    val lastname: String = "",
    val userGroup: UserGroup = UserGroup.TEACHER,
    val sports: MutableSet<Sport> = mutableSetOf(),
    val profilePicturePath: String = "",
) {
    fun updateUser(user: User) {
        if (email != user.email && email.isNotEmpty()) {
            user.email = email
        }
        if (password != user.password && password.isNotEmpty()) {
            user.password = password
        }
        if (firstname != user.firstname && firstname.isNotEmpty()) {
            user.firstname = firstname
        }
        if (lastname != user.lastname && lastname.isNotEmpty()) {
            user.lastname = lastname
        }
        if (userGroup != user.userGroup) {
            user.userGroup = userGroup
        }
        if (sports != user.sports) {
            user.sports = sports
        }
        if (profilePicturePath != user.profilePicturePath) {
            user.profilePicturePath = profilePicturePath
        }
    }
}