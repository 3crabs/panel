package tech.crabs

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.roles.RoleCreate
import javax.inject.Inject

@MicronautTest
class PanelTest : StringSpec() {

    @Inject
    lateinit var roleClient: RoleClient

    init {
        "Получение списка ролей с 0 элементов" {
            roleClient.getAllRoles().size shouldBe 0
        }

        "Создание роли" {
            val r = roleClient.createRole(RoleCreate("code", "name"))
            r.name shouldBe "name"
            r.code shouldBe "code"
            r.created.shouldNotBeNull()
        }

        "Получение списка ролей с 1 элементом" {
            roleClient.getAllRoles().size shouldBe 1
        }
    }
}
