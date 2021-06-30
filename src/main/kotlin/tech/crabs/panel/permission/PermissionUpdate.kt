package tech.crabs.panel.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Разрешение (редактирование)")
data class PermissionUpdate(

    /**
     * название разрешения (уникально)
     */
    val name: String
)
