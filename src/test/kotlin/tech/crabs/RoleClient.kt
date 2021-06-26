package tech.crabs

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.crabs.roles.RoleCreate
import tech.crabs.roles.RoleInfo

@Client("/roles")
interface RoleClient {

    @Get
    fun getAllRoles(): List<RoleInfo>

    @Post
    fun createRole(role: RoleCreate): RoleInfo
}
