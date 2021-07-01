package tech.crabs.panel.permission

import javax.inject.Singleton

@Singleton
class PermissionConverter {

    fun convert(o: PermissionEntity) = PermissionInfo(
        o.code,
        o.functionCode,
        o.roleCode,
        o.created
    )
}
