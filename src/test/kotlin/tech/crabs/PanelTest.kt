package tech.crabs

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.roles.RoleCreate
import tech.crabs.panel.roles.RoleRepository
import javax.inject.Inject

@MicronautTest
class PanelTest : StringSpec() {

    @Inject
    lateinit var roleClient: RoleClient

    @Inject
    lateinit var roleRepository: RoleRepository

    init {
        "Получение списка ролей без элементов" {
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

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        roleRepository.deleteAll()
    }
}
