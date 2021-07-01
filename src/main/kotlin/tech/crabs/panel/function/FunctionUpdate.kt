package tech.crabs.panel.function

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Функциия (редактирование)")
data class FunctionUpdate(

    /**
     * название функциии (уникально)
     */
    val name: String
)
