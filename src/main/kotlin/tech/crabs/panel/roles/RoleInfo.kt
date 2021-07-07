package tech.crabs.panel.roles

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema
import tech.crabs.panel.JsonLocalDateTimeDeserializer
import tech.crabs.panel.JsonLocalDateTimeSerializer
import java.time.LocalDateTime

@Schema(description = "Роль")
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
    @JsonSerialize(using = JsonLocalDateTimeSerializer::class)
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer::class)
    val created: LocalDateTime
)
