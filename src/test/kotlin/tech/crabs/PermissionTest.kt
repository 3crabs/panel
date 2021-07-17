package tech.crabs

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.function.FunctionCreate
import tech.crabs.panel.function.FunctionRepository
import tech.crabs.panel.permission.PermissionCreate
import tech.crabs.panel.permission.PermissionRepository
import tech.crabs.panel.roles.RoleCreate
import tech.crabs.panel.roles.RoleRepository
import javax.inject.Inject

@MicronautTest
class PermissionTest : StringSpec() {

    @Inject
    lateinit var roleClient: RoleClient

    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var functionClient: FunctionClient

    @Inject
    lateinit var functionRepository: FunctionRepository

    @Inject
    lateinit var permissionClient: PermissionClient

    @Inject
    lateinit var permissionRepository: PermissionRepository

    init {
        "Получение списка разрешений без элементов" {
            permissionClient.getAllPermissions().size shouldBe 0
        }

        "Создание роли" {
            roleClient.createRole(RoleCreate("role_code", "name"))
        }

        "Создание функции" {
            functionClient.createFunction(FunctionCreate("function_code", "name"))
        }

        "Создание разрешения" {
            val p = permissionClient.addPermission(PermissionCreate("role_code", "function_code"))
            p.code shouldBe "role_code__function_code"
            p.roleCode shouldBe "role_code"
            p.functionCode shouldBe "function_code"
            p.created.shouldNotBeNull()
        }

        "Получение списка разрешений с одним элементом" {
            permissionClient.getAllPermissions().size shouldBe 1
        }

        "Получение своих разрешений" {
            permissionClient.getAllPermissions("role_code").size shouldBe 1
        }

        "Получение не своих разрешений" {
            permissionClient.getAllPermissions("bad_role_code").size shouldBe 0
        }

        "Удаление разрешения по коду" {
            val p = permissionClient.deletePermissionByCode("role_code__function_code")
            p.code shouldBe "role_code__function_code"
            p.roleCode shouldBe "role_code"
            p.functionCode shouldBe "function_code"
            p.created.shouldNotBeNull()
        }

        "Получение списка разрешений без элементов после удаления" {
            permissionClient.getAllPermissions().size shouldBe 0
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        permissionRepository.deleteAll()
        functionRepository.deleteAll()
        roleRepository.deleteAll()
    }
}
