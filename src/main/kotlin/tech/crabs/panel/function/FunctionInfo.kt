package tech.crabs.panel.function

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema
import tech.crabs.panel.JsonLocalDateTimeDeserializer
import tech.crabs.panel.JsonLocalDateTimeSerializer
import java.time.LocalDateTime

@Schema(description = "Функциия")
data class FunctionInfo(

    /**
     * код функциии (уникально)
     */
    val code: String,

    /**
     * название функциии (уникально)
     */
    val name: String,

    /**
     * время и дата создания функциии
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer::class)
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer::class)
    val created: LocalDateTime
)
