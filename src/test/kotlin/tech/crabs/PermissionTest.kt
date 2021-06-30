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
import tech.crabs.panel.permission.PermissionCreate
import tech.crabs.panel.permission.PermissionRepository
import tech.crabs.panel.permission.PermissionUpdate
import javax.inject.Inject

@MicronautTest
class PermissionTest : StringSpec() {

    @Inject
    lateinit var permissionClient: PermissionClient

    @Inject
    lateinit var permissionRepository: PermissionRepository

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

        "Создание разрешения code 2" {
            val r = permissionClient.createPermission(PermissionCreate(" code 2 ", " name 2 "))
            r.name shouldBe "name 2"
            r.code shouldBe "code 2"
            r.created.shouldNotBeNull()
        }

        "Попытка создание разрешения с кодом который уже существует заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                permissionClient.createPermission(PermissionCreate(" code 1 ", "name"))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("code is already in use")
        }

        "Попытка создание разрешения с именем которое уже существует заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                permissionClient.createPermission(PermissionCreate("code", " name 1 "))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("name is already in use")
        }

        "Получение списка разрешений с 2 элементами" {
            permissionClient.getAllPermissions().size shouldBe 2
        }

        "Получение разрешения по коду" {
            val p = permissionClient.getPermissionByCode("code 1")
            p.name shouldBe "name 1"
            p.code shouldBe "code 1"
            p.created.shouldNotBeNull()
        }

        "Попытка получение разрешения по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                permissionClient.getPermissionByCode("bad_code")
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "permission not found"
        }

        "Редактирование разрешения" {
            var p = permissionClient.updatePermission("code 1", PermissionUpdate(" name 1 new "))
            p.name shouldBe "name 1 new"
            p.code shouldBe "code 1"
            p.created.shouldNotBeNull()
            p = permissionClient.getPermissionByCode("code 1")
            p.name shouldBe "name 1 new"
            p.code shouldBe "code 1"
            p.created.shouldNotBeNull()
        }

        "Попытка редактирования разрешения по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                permissionClient.updatePermission("bad_code", PermissionUpdate("name"))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "permission not found"
        }

        "Попытка присвоения разрешению имени которое уже есть в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                permissionClient.updatePermission("code 2", PermissionUpdate(" name 1 new "))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "name is already in use"
        }

        "Присвоение разрешению своего же имени" {
            val p = permissionClient.updatePermission("code 2", PermissionUpdate(" name 2 "))
            p.name shouldBe "name 2"
            p.code shouldBe "code 2"
            p.created.shouldNotBeNull()
        }

        "Удаление разрешения code 1"{
            val p = permissionClient.deletePermissionByCode("code 1")
            p.name shouldBe "name 1 new"
            p.code shouldBe "code 1"
            p.created.shouldNotBeNull()
        }

        "Попытка удаление разрешения по коду которого нет в базе заканчивается ошибкой"{
            val e = shouldThrow<HttpClientResponseException> {
                permissionClient.deletePermissionByCode("bad_code")
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "permission not found"
        }

        "Получение списка разрешений с 1 элементом"{
            permissionClient.getAllPermissions().size shouldBe 1
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        permissionRepository.deleteAll()
    }
}
