package tech.crabs.panel.data

import tech.crabs.panel.function.FunctionService
import tech.crabs.panel.maker.MakerService
import tech.crabs.panel.permission.PermissionService
import tech.crabs.panel.roles.RoleService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataService {

    @Inject
    lateinit var roleService: RoleService

    @Inject
    lateinit var functionService: FunctionService

    @Inject
    lateinit var permissionService: PermissionService

    @Inject
    lateinit var makerService: MakerService

    fun getData(): Data = Data(
        roleService.getAllRoles(),
        functionService.getAllFunctions(),
        permissionService.getAllPermissions(),
        makerService.getAllMakers()
    )

    fun loadData(data: Data): Data {
        makerService.deleteAllMarkers()
        permissionService.deleteAllPermissions()
        functionService.deleteAllFunctions()
        roleService.deleteAllRoles()
        return Data(
            roleService.addRoles(data.roles),
            functionService.addFunctions(data.functions),
            permissionService.addPermissions(data.permissions),
            makerService.addMakers(data.makers)
        )
    }
}
