package tech.crabs.panel.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Разрешение (создание)")
data class PermissionCreate(

    /**
     * код разрешения (уникально)
     */
    val code: String,

    /**
     * название разрешения (уникально)
     */
    val name: String
)
