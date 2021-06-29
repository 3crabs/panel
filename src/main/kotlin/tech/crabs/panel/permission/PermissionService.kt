package tech.crabs.panel.permission

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

    fun createPermission(permission: PermissionCreate) =
        permissionConverter.convert(permissionRepository.save(permissionConverter.convert(permission)))
}
