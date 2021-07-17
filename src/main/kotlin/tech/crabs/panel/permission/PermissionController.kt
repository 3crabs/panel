package tech.crabs.panel.permission

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller(value = "/permissions")
@Tag(name = "Разрешения")
class PermissionController {

    @Inject
    lateinit var permissionService: PermissionService

    @Get(value = "{?roleCode}")
    @Operation(summary = "Получение разрешений")
    fun getAllPermissions(roleCode: String? = null) = permissionService.getAllPermissions(roleCode)

    @Post
    @Operation(summary = "Создание разрешения")
    fun addPermission(permission: PermissionCreate) = permissionService.addPermission(permission)

    @Delete(value = "/{code}")
    @Operation(summary = "Удаление разрешения")
    fun deletePermissionByCode(code: String) = permissionService.deletePermissionByCode(code)
}
