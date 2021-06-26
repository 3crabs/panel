package tech.crabs.roles

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoleService {

    @Inject
    lateinit var roleConverter: RoleConverter

    var list = mutableListOf<RoleEntity>()

    fun getAllRoles() = list.map { roleConverter.convert(it) }

    fun createRole(role: RoleCreate) = roleConverter.convert(role).let {
        list.add(it)
        roleConverter.convert(it)
    }
}
