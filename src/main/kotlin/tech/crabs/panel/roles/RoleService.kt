package tech.crabs.panel.roles

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoleService {

    @Inject
    lateinit var roleConverter: RoleConverter

    @Inject
    lateinit var roleRepository: RoleRepository

    fun getAllRoles() = roleRepository.findAll().map { roleConverter.convert(it) }

    fun createRole(role: RoleCreate): RoleInfo {
        roleRepository.findByCode(role.code)?.let { throw badRequest("code is already in use") }
        return roleConverter.convert(roleRepository.save(roleConverter.convert(role)))
    }

    fun getRoleByCode(code: String): RoleInfo {
        val r = roleRepository.findByCode(code) ?: throw badRequest("role not found")
        return roleConverter.convert(r)
    }

    fun updateRole(code: String, role: RoleUpdate): RoleInfo {
        val r = roleRepository.findByCode(code) ?: throw badRequest("role not found")
        r.name = role.name
        return roleConverter.convert(roleRepository.update(r))
    }

    fun deleteRoleByCode(code: String): RoleInfo {
        val r = roleRepository.findByCode(code) ?: throw badRequest("role not found")
        roleRepository.delete(r)
        return roleConverter.convert(r)
    }

    private fun badRequest(message: String) = HttpStatusException(HttpStatus.BAD_REQUEST, message)
}
