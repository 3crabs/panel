package tech.crabs

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.roles.RoleCreate
import tech.crabs.panel.roles.RoleInfo

@Client("/roles")
interface RoleClient {

    @Get
    fun getAllRoles(): List<RoleInfo>

    @Post
    fun createRole(role: RoleCreate): RoleInfo
}
