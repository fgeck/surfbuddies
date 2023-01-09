package com.fgeck.surfbuddies.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


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
    @field:NotBlank @field:Email(
        message = "Email is not valid",
        regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    )
    var email: String,
    @field:NotBlank @field:JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private var password: String,
    @Id
    var id: ObjectId = ObjectId.get(),
    var schoolname: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var userGroup: UserGroup = UserGroup.TEACHER,
    var sports: MutableSet<Sport> = mutableSetOf(),
    var profilePicturePath: String = "",

    var roles: MutableSet<Role> = mutableSetOf(Role.USER),
) : UserDetails {
    private var enabled: Boolean = true
    private var accountNonExpired: Boolean = true
    private var credentialsNonExpired: Boolean = true
    private var accountNonLocked: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableSet<GrantedAuthority> = mutableSetOf()
        this.roles.forEach {
            authorities.add(
                SimpleGrantedAuthority(it.name)
            )
        }
        return authorities
    }

    fun setAuthorities(authorities: MutableSet<GrantedAuthority>) {
        val newRoles = mutableSetOf<Role>()
        authorities.forEach { newRoles.add(Role.valueOf(it.authority.toString())) }
        this.roles = newRoles
    }

    override fun getPassword(): String {
        return this.password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername(): String {
        return this.email
    }

    fun setUsername(username: String) {
        this.email = username
    }

    override fun isAccountNonExpired(): Boolean {
        return this.accountNonExpired
    }

    fun setAccountNonExpired(accountNonExpired: Boolean) {
        this.accountNonExpired = accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return this.accountNonLocked
    }

    fun setAccountNonLocked(accountNonLocked: Boolean) {
        this.accountNonLocked = accountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.credentialsNonExpired
    }

    fun setCredentialsNonExpired(credentialsNonExpired: Boolean) {
        this.credentialsNonExpired = credentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }
}
