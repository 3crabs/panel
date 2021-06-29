package tech.crabs.panel.roles

import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller(value = "/roles")
class RoleController {

    @Inject
    lateinit var roleService: RoleService

    @Get
    fun getAllRoles() = roleService.getAllRoles()

    @Post
    fun createRole(role: RoleCreate) = roleService.createRole(role)

    @Get(value = "/{code}")
    fun getRoleByCode(code: String) = roleService.getRoleByCode(code)

    @Put(value = "/{code}")
    fun updateRole(code: String, role: RoleUpdate) = roleService.updateRole(code, role)

    @Delete(value = "/{code}")
    fun deleteRoleByCode(code: String) = roleService.deleteRoleByCode(code)
}
