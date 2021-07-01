package tech.crabs.panel.permission

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionService {

    @Inject
    lateinit var permissionRepository: PermissionRepository

    @Inject
    lateinit var permissionConverter: PermissionConverter

    fun getAllPermissions(): List<PermissionInfo> {
        return permissionRepository.findAll().map { permissionConverter.convert(it) }
    }
}