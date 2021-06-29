package tech.crabs.panel.roles

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoleService {

    @Inject
    lateinit var roleConverter: RoleConverter

    @Inject
    lateinit var roleRepository: RoleRepository

    fun getAllRoles() = roleRepository.findAll().map { roleConverter.convert(it) }

    fun createRole(role: RoleCreate) =
        roleConverter.convert(role).let { roleConverter.convert(roleRepository.save(it)) }
}
