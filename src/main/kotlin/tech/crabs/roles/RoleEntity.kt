package tech.crabs.roles

import java.time.LocalDateTime

data class RoleEntity(

    var code: String,

    var name: String,

    var created: LocalDateTime
)
