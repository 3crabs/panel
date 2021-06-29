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
}
