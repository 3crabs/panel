package tech.crabs.panel.function

import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class FunctionConverter {

    fun convert(o: FunctionCreate) = FunctionEntity(
        o.code.trim(),
        o.name.trim(),
        LocalDateTime.now(Clock.systemUTC())
    )

    fun convert(o: FunctionEntity) = FunctionInfo(
        o.code,
        o.name,
        o.created
    )
}
