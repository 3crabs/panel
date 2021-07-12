package tech.crabs.panel.maker

import java.time.Clock
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "makers")
data class MakerEntity(

    @Id
    var code: String,

    val roleCodeWho: String,

    val roleCodeWhom: String,

    var created: LocalDateTime
) {
    constructor() : this("", "", "", LocalDateTime.now(Clock.systemUTC()))
}
