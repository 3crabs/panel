package tech.crabs.panel.data

import io.swagger.v3.oas.annotations.media.Schema
import tech.crabs.panel.function.FunctionInfo
import tech.crabs.panel.maker.MakerInfo
import tech.crabs.panel.permission.PermissionInfo
import tech.crabs.panel.roles.RoleInfo

@Schema(description = "Информация")
data class Data(

    /**
     * роли
     */
    val roles: List<RoleInfo>,

    /**
     * функции
     */
    val functions: List<FunctionInfo>,

    /**
     * разрешения
     */
    val permissions: List<PermissionInfo>,

    /**
     * создатели
     */
    val makers: List<MakerInfo>,
)
