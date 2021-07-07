package tech.crabs.panel

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class JsonLocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {

    override fun serialize(value: LocalDateTime, gen: JsonGenerator, provider: SerializerProvider) {
        val zdt = ZonedDateTime.of(value, ZoneOffset.UTC)
        gen.writeString(zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    }
}
