package tech.crabs.panel.permission

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import tech.crabs.panel.function.FunctionRepository
import tech.crabs.panel.roles.RoleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionService {

    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var functionRepository: FunctionRepository

    @Inject
    lateinit var permissionRepository: PermissionRepository

    @Inject
    lateinit var permissionConverter: PermissionConverter

    fun getAllPermissions(): List<PermissionInfo> {
        return permissionRepository.findAll().map { permissionConverter.convert(it) }
    }

    fun addPermission(permission: PermissionCreate): PermissionInfo {
        roleRepository.findByCode(permission.roleCode) ?: throw badRequest("role not found")
        functionRepository.findByCode(permission.functionCode) ?: throw badRequest("function not found")
        return permissionConverter.convert(permissionRepository.save(permissionConverter.convert(permission)))
    }

    fun deletePermissionByCode(code: String): PermissionInfo {
        val p = permissionRepository.findByCode(code) ?: throw badRequest("permission not found")
        permissionRepository.delete(p)
        return permissionConverter.convert(p)
    }

    private fun badRequest(message: String) = HttpStatusException(HttpStatus.BAD_REQUEST, message)
}
