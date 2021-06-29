package tech.crabs.panel.roles

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Роль пользователя внешней системы (создание)")
data class RoleCreate(

    /**
     * код роли (уникально)
     */
    val code: String,

    /**
     * название роли (уникально)
     */
    val name: String
)
