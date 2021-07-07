package tech.crabs.panel.permission

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema
import tech.crabs.panel.JsonLocalDateTimeDeserializer
import tech.crabs.panel.JsonLocalDateTimeSerializer
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
    @JsonSerialize(using = JsonLocalDateTimeSerializer::class)
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer::class)
    val created: LocalDateTime
)
