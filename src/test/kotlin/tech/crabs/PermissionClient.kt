package tech.crabs

import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.permission.PermissionCreate
import tech.crabs.panel.permission.PermissionInfo
import tech.crabs.panel.permission.PermissionUpdate

@Client(value = "/permissions")
interface PermissionClient {

    @Get
    fun getAllPermissions(): List<PermissionInfo>

    @Post
    fun createPermission(permission: PermissionCreate): PermissionInfo

    @Get(value = "/{code}")
    fun getPermissionByCode(code: String): PermissionInfo

    @Put(value = "/{code}")
    fun updatePermission(code: String, permission: PermissionUpdate): PermissionInfo

    @Delete(value = "/{code}")
    fun deletePermissionByCode(code: String): PermissionInfo
}
