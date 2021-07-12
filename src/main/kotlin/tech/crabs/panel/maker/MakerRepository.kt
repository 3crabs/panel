package tech.crabs.panel.maker

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface MakerRepository : CrudRepository<MakerEntity, String> {

    fun findByCode(code: String): MakerEntity?
}
