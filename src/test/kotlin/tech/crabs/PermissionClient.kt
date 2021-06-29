package tech.crabs

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.permission.PermissionCreate
import tech.crabs.panel.permission.PermissionInfo

@Client(value = "/permissions")
interface PermissionClient {

    @Get
    fun getAllPermissions(): List<PermissionInfo>

    @Post
    fun createPermission(permission: PermissionCreate): PermissionInfo
}
