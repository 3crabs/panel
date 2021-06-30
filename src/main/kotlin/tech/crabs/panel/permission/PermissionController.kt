package tech.crabs.panel.permission

import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller(value = "/permissions")
@Tag(name = "Разрешения")
class PermissionController {

    @Inject
    lateinit var permissionService: PermissionService

    @Get
    @Operation(summary = "Получение разрешений")
    fun getAllPermissions() = permissionService.getAllPermissions()

    @Post
    @Operation(summary = "Создание разрешения")
    fun createPermission(permission: PermissionCreate) = permissionService.createPermission(permission)

    @Get(value = "/{code}")
    @Operation(summary = "Получение разрешения по коду")
    fun getPermissionByCode(code: String): PermissionInfo = permissionService.getPermissionByCode(code)

    @Put(value = "/{code}")
    @Operation(summary = "Редактирование разрешения по коду")
    fun updatePermission(code: String, permission: PermissionUpdate) =
        permissionService.updatePermission(code, permission)

    @Delete(value = "/{code}")
    @Operation(summary = "Удаление разрешения по коду")
    fun deletePermissionByCode(code: String) = permissionService.deletePermissionByCode(code)
}
