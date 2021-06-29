package tech.crabs

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.permission.PermissionCreate
import javax.inject.Inject

@MicronautTest
class PermissionTest : StringSpec() {

    @Inject
    lateinit var permissionClient: PermissionClient

    init {
        "Получение списка разрешений без элементов" {
            permissionClient.getAllPermissions().size shouldBe 0
        }

        "Создание разрешения code 1" {
            val p = permissionClient.createPermission(PermissionCreate(" code 1 ", " name 1 "))
            p.name shouldBe "name 1"
            p.code shouldBe "code 1"
            p.created.shouldNotBeNull()
        }
    }
}
