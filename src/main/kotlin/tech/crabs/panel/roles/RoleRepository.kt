package tech.crabs.panel.roles

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface RoleRepository : CrudRepository<RoleEntity, String> {

    fun findByCode(code: String): RoleEntity?

    fun findByName(name: String): RoleEntity?
}
