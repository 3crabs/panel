package tech.crabs.panel.permission

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Разрешение")
data class PermissionInfo(

    /**
     * код разрешения (уникально)
     */
    val code: String,

    /**
     * название разрешения (уникально)
     */
    val name: String,

    /**
     * время и дата создания разрешения
     */
    val created: LocalDateTime
)
