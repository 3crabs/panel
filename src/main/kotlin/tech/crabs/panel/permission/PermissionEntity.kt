package tech.crabs.panel.permission

import java.time.Clock
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "permissions")
data class PermissionEntity(

    @Id
    var code: String,

    var name: String,

    var created: LocalDateTime
) {
    constructor() : this("", "", LocalDateTime.now(Clock.systemUTC()))
}
