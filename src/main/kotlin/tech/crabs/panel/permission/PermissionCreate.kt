package tech.crabs.panel.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Роль (создание)")
data class PermissionCreate(

    /**
     * код роли
     */
    val roleCode: String,

    /**
     * код функциии
     */
    val functionCode: String
)
