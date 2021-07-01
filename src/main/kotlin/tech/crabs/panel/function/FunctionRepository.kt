package tech.crabs.panel.function

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface FunctionRepository : CrudRepository<FunctionEntity, String> {

    fun findByCode(code: String): FunctionEntity?

    fun findByName(name: String): FunctionEntity?

    @Suppress("MicronautDataMethodInconsistencyInspection")
    fun findByCodeNotEqualAndName(code: String, name: String): FunctionEntity?
}
