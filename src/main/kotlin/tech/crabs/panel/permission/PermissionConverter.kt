package tech.crabs.panel.permission

import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class PermissionConverter {

    fun convert(o: PermissionEntity) = PermissionInfo(
        o.code,
        o.functionCode,
        o.roleCode,
        o.created
    )

    fun convert(o: PermissionCreate) = PermissionEntity(
        o.roleCode + "__" + o.functionCode,
        o.functionCode,
        o.roleCode,
        LocalDateTime.now(Clock.systemUTC())
    )

    fun convert(o: PermissionInfo) = PermissionEntity(
        o.roleCode + "__" + o.functionCode,
        o.functionCode,
        o.roleCode,
        LocalDateTime.now(Clock.systemUTC())
    )
}
