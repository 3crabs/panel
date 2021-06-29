package tech.crabs.panel.roles

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Роль пользователя внешней системы (редактирование)")
data class RoleUpdate(

    /**
     * название роли (уникально)
     */
    val name: String
)
