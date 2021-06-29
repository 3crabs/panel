package tech.crabs

import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.roles.RoleCreate
import tech.crabs.panel.roles.RoleInfo
import tech.crabs.panel.roles.RoleUpdate

@Client(value = "/roles")
interface RoleClient {

    @Get
    fun getAllRoles(): List<RoleInfo>

    @Post
    fun createRole(role: RoleCreate): RoleInfo

    @Get(value = "/{code}")
    fun getRoleByCode(code: String): RoleInfo

    @Put(value = "/{code}")
    fun updateRole(code: String, role: RoleUpdate): RoleInfo

    @Delete(value = "/{code}")
    fun deleteRoleByCode(code: String): RoleInfo
}
