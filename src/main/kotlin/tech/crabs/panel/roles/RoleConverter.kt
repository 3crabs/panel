package tech.crabs.panel.roles

import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class RoleConverter {

    fun convert(o: RoleCreate) = RoleEntity(
        o.code.trim(),
        o.name.trim(),
        LocalDateTime.now(Clock.systemUTC())
    )

    fun convert(o: RoleEntity) = RoleInfo(
        o.code,
        o.name,
        o.created
    )
}
