package tech.crabs.panel.function

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Функциия (создание)")
data class FunctionCreate(

    /**
     * код функциии (уникально)
     */
    val code: String,

    /**
     * название функциии (уникально)
     */
    val name: String
)
