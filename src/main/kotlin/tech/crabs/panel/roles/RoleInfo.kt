package tech.crabs.panel.roles

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Роль пользователя внешней системы")
data class RoleInfo(

    /**
     * код роли (уникально)
     */
    val code: String,

    /**
     * название роли (уникально)
     */
    val name: String,

    /**
     * время и дата создания роли
     */
    val created: LocalDateTime
)
