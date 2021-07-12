package tech.crabs.panel.maker

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema
import tech.crabs.panel.JsonLocalDateTimeDeserializer
import tech.crabs.panel.JsonLocalDateTimeSerializer
import java.time.LocalDateTime

@Schema(description = "Создатель")
data class MakerInfo(

    /**
     * идентификатор создателя
     */
    val code: String,

    /**
     * код роли создалеля
     */
    val roleCodeWho: String,

    /**
     * код роли создаваемого
     */
    val roleCodeWhom: String,

    /**
     * время и дата создания создателя
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer::class)
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer::class)
    val created: LocalDateTime
)
