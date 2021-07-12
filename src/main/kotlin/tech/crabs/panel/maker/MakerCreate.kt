package tech.crabs.panel.maker

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Создатель (создание)")
data class MakerCreate(

    /**
     * код роли создалеля
     */
    val roleCodeWho: String,

    /**
     * код роли создаваемого
     */
    val roleCodeWhom: String
)
