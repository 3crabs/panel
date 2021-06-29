package tech.crabs.panel.roles

import java.time.LocalDateTime

/**
 * роль пользователя внешней системы
 */
data class RoleInfo(

    /**
     * код роли
     * уникально
     */
    val code: String,

    /**
     * название роли
     * уникально
     */
    val name: String,

    /**
     * время и дата создания роли
     */
    val created: LocalDateTime
)
