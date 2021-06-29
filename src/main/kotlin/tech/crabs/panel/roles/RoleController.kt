package tech.crabs.panel.roles

import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller(value = "/roles")
@Tag(name = "Роли")
class RoleController {

    @Inject
    lateinit var roleService: RoleService

    @Get
    @Operation(summary = "Получение ролей")
    fun getAllRoles() = roleService.getAllRoles()

    @Post
    @Operation(summary = "Создание роли")
    fun createRole(role: RoleCreate) = roleService.createRole(role)

    @Get(value = "/{code}")
    @Operation(summary = "Получение роли по коду")
    fun getRoleByCode(code: String) = roleService.getRoleByCode(code)

    @Put(value = "/{code}")
    @Operation(summary = "Редактирование роли по коду")
    fun updateRole(code: String, role: RoleUpdate) = roleService.updateRole(code, role)

    @Delete(value = "/{code}")
    @Operation(summary = "Удаление роли по коду")
    fun deleteRoleByCode(code: String) = roleService.deleteRoleByCode(code)
}
