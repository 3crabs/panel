package tech.crabs.panel

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JsonLocalDateTimeDeserializer : JsonDeserializer<LocalDateTime?>() {

    override fun deserialize(p: JsonParser, ctx: DeserializationContext?): LocalDateTime {
        return LocalDateTime.parse(p.text, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}
