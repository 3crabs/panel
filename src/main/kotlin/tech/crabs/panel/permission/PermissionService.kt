package tech.crabs.panel.permission

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionService {

    @Inject
    lateinit var permissionConverter: PermissionConverter

    @Inject
    lateinit var permissionRepository: PermissionRepository

    fun getAllPermissions(): List<PermissionInfo> =
        permissionRepository.findAll().map { permissionConverter.convert(it) }

    fun createPermission(permission: PermissionCreate): PermissionInfo {
        permissionRepository.findByCode(permission.code.trim())?.let { throw badRequest("code is already in use") }
        permissionRepository.findByName(permission.name.trim())?.let { throw badRequest("name is already in use") }
        return permissionConverter.convert(permissionRepository.save(permissionConverter.convert(permission)))
    }

    fun getPermissionByCode(code: String): PermissionInfo {
        val p = permissionRepository.findByCode(code) ?: throw badRequest("permission not found")
        return permissionConverter.convert(p)
    }

    fun updatePermission(code: String, permission: PermissionUpdate): PermissionInfo {
        val r = permissionRepository.findByCode(code) ?: throw badRequest("permission not found")
        val name = permission.name.trim()
        permissionRepository.findByCodeNotEqualAndName(code, name)?.let { throw badRequest("name is already in use") }
        r.name = name
        return permissionConverter.convert(permissionRepository.update(r))
    }

    fun deletePermissionByCode(code: String): PermissionInfo {
        val r = permissionRepository.findByCode(code) ?: throw badRequest("permission not found")
        permissionRepository.delete(r)
        return permissionConverter.convert(r)
    }

    private fun badRequest(message: String) = HttpStatusException(HttpStatus.BAD_REQUEST, message)
}
