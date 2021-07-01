package tech.crabs.panel.permission

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Разрешение")
data class PermissionInfo(

    /**
     * идентификатор разрешения
     */
    val code: String,

    /**
     * идентификатор функции разрешения
     */
    val functionId: String,

    /**
     * идентификатор роли разрешения
     */
    val roleId: String,

    /**
     * время и дата создания разрешения
     */
    val created: LocalDateTime
)
