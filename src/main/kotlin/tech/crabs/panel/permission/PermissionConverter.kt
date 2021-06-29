package tech.crabs.panel.permission

import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class PermissionConverter {

    fun convert(o: PermissionCreate) = PermissionEntity(
        o.code.trim(),
        o.name.trim(),
        LocalDateTime.now(Clock.systemUTC())
    )

    fun convert(o: PermissionEntity) = PermissionInfo(
        o.code,
        o.name,
        o.created
    )
}
