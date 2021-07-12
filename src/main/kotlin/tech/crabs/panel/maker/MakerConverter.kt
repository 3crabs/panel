package tech.crabs.panel.maker

import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class MakerConverter {

    fun convert(o: MakerEntity) = MakerInfo(
        o.code,
        o.roleCodeWho,
        o.roleCodeWhom,
        o.created
    )

    fun convert(o: MakerCreate) = MakerEntity(
        o.roleCodeWho + "__" + o.roleCodeWhom,
        o.roleCodeWho,
        o.roleCodeWhom,
        LocalDateTime.now(Clock.systemUTC())
    )
}
