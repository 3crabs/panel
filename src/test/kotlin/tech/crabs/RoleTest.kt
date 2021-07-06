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
class RoleTest : StringSpec() {

    @Inject
    lateinit var roleClient: RoleClient

    @Inject
    lateinit var roleRepository: RoleRepository

    init {
        "Получение списка ролей без элементов" {
            roleClient.getAllRoles().size shouldBe 0
        }

        "Создание роли code 1" {
            val r = roleClient.createRole(RoleCreate(" code 1 ", " name 1 "))
            r.name shouldBe "name 1"
            r.code shouldBe "code 1"
            r.created.shouldNotBeNull()
        }

        "Создание роли code 2" {
            val r = roleClient.createRole(RoleCreate(" code 2 ", " name 2 "))
            r.name shouldBe "name 2"
            r.code shouldBe "code 2"
            r.created.shouldNotBeNull()
        }

        "Попытка создание роли с кодом который уже существует заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                roleClient.createRole(RoleCreate(" code 1 ", "name"))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("code is already in use")
        }

        "Попытка создание роли с именем которое уже существует заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                roleClient.createRole(RoleCreate("code", " name 1 "))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("name is already in use")
        }

        "Получение списка ролей с 2 элементами" {
            roleClient.getAllRoles().size shouldBe 2
        }

        "Получение роли по коду" {
            val r = roleClient.getRoleByCode("code 1")
            r.name shouldBe "name 1"
            r.code shouldBe "code 1"
            r.created.shouldNotBeNull()
        }

        "Попытка получение роли по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                roleClient.getRoleByCode("bad_code")
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "role not found"
        }

        "Редактирование роли" {
            var r = roleClient.updateRole("code 1", RoleUpdate(" name 1 new "))
            r.name shouldBe "name 1 new"
            r.code shouldBe "code 1"
            r.created.shouldNotBeNull()
            r = roleClient.getRoleByCode("code 1")
            r.name shouldBe "name 1 new"
            r.code shouldBe "code 1"
            r.created.shouldNotBeNull()
        }

        "Попытка редактирования роли по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                roleClient.updateRole("bad_code", RoleUpdate("name"))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "role not found"
        }

        "Попытка присвоения роли имени которое уже есть в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                roleClient.updateRole("code 2", RoleUpdate(" name 1 new "))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "name is already in use"
        }

        "Присвоение роли своего же имени" {
            val r = roleClient.updateRole("code 2", RoleUpdate(" name 2 "))
            r.name shouldBe "name 2"
            r.code shouldBe "code 2"
            r.created.shouldNotBeNull()
        }

        "Удаление роли code 1" {
            val r = roleClient.deleteRoleByCode("code 1")
            r.name shouldBe "name 1 new"
            r.code shouldBe "code 1"
            r.created.shouldNotBeNull()
        }

        "Попытка удаление роли по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                roleClient.deleteRoleByCode("bad_code")
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "role not found"
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
