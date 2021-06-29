package tech.crabs.panel.roles

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/roles")
class RoleController {

    @Inject
    lateinit var roleService: RoleService

    @Get
    fun getAllRoles() = roleService.getAllRoles()

    @Post
    fun createRole(role: RoleCreate) = roleService.createRole(role)
}
