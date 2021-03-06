package tech.crabs.panel.permission

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface PermissionRepository : CrudRepository<PermissionEntity, String> {

    fun findByCode(code: String): PermissionEntity?

    fun findAllByRoleCode(roleCode: String): List<PermissionEntity>
}
