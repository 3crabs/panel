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
     * код функциии
     */
    val functionCode: String,

    /**
     * код роли
     */
    val roleCode: String,

    /**
     * время и дата создания разрешения
     */
    val created: LocalDateTime
)
