package tech.crabs.panel.function

import io.swagger.v3.oas.annotations.media.Schema
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
    val created: LocalDateTime
)
