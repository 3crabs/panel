package tech.crabs

import io.micronaut.http.annotation.Delete
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
    fun addPermission(permission: PermissionCreate): PermissionInfo

    @Delete(value = "/{code}")
    fun deletePermissionByCode(code: String): PermissionInfo
}
