package tech.crabs

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.roles.RoleCreate
import tech.crabs.panel.roles.RoleRepository
import tech.crabs.panel.roles.RoleUpdate
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

        "Попытка создание роли с кодо который уже существует заканчивается ошибкой" {
            val e =
                shouldThrow<HttpClientResponseException> { roleClient.createRole(RoleCreate("code", "new name")) }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("code is already in use")
        }

        "Получение списка ролей с 1 элементом" {
            roleClient.getAllRoles().size shouldBe 1
        }

        "Получение роли по коду" {
            val r = roleClient.getRoleByCode("code")
            r.name shouldBe "name"
            r.code shouldBe "code"
            r.created.shouldNotBeNull()
        }

        "Попытка получение роли по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> { roleClient.getRoleByCode("bad_code") }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "role not found"
        }

        "Редактирование роли" {
            var r = roleClient.updateRole("code", RoleUpdate("new name"))
            r.name shouldBe "new name"
            r.code shouldBe "code"
            r.created.shouldNotBeNull()
            r = roleClient.getRoleByCode("code")
            r.name shouldBe "new name"
            r.code shouldBe "code"
            r.created.shouldNotBeNull()
        }

        "Попытка редактирования роли по коду которого нет в базе заканчивается ошибкой" {
            val e =
                shouldThrow<HttpClientResponseException> { roleClient.updateRole("bad_code", RoleUpdate("new name")) }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "role not found"
        }

        "Удаление роли" {
            val r = roleClient.deleteRoleByCode("code")
            r.name shouldBe "new name"
            r.code shouldBe "code"
            r.created.shouldNotBeNull()
        }

        "Попытка получение роли по коду удаленной роли заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> { roleClient.getRoleByCode("code") }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "role not found"
        }

        "Получение списка ролей без элементов (снова)" {
            roleClient.getAllRoles().size shouldBe 0
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        roleRepository.deleteAll()
    }
}
