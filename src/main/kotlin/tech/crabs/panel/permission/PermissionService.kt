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

    private fun badRequest(message: String) = HttpStatusException(HttpStatus.BAD_REQUEST, message)
}
