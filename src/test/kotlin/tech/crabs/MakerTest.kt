package tech.crabs

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.maker.MakerCreate
import tech.crabs.panel.maker.MakerRepository
import tech.crabs.panel.roles.RoleCreate
import tech.crabs.panel.roles.RoleRepository
import javax.inject.Inject

@MicronautTest
class MakerTest : StringSpec() {

    @Inject
    lateinit var makerClient: MakerClient

    @Inject
    lateinit var roleClient: RoleClient

    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var makerRepository: MakerRepository

    init {
        "Получение списка создателей без элементов" {
            makerClient.getAllMakers().size shouldBe 0
        }

        "Создание ролей" {
            roleClient.createRole(RoleCreate("role_code_1", "name_1"))
            roleClient.createRole(RoleCreate("role_code_2", "name_2"))
        }

        "Создание создателя" {
            val p = makerClient.addMaker(MakerCreate("role_code_1", "role_code_2"))
            p.code shouldBe "role_code_1__role_code_2"
            p.roleCodeWho shouldBe "role_code_1"
            p.roleCodeWhom shouldBe "role_code_2"
            p.created.shouldNotBeNull()
        }

        "Получение списка разрешений с одним элементом" {
            makerClient.getAllMakers().size shouldBe 1
        }

        "Удаление разрешения по коду" {
            val p = makerClient.deleteMakerByCode("role_code_1__role_code_2")
            p.code shouldBe "role_code_1__role_code_2"
            p.roleCodeWho shouldBe "role_code_1"
            p.roleCodeWhom shouldBe "role_code_2"
            p.created.shouldNotBeNull()
        }

        "Получение списка разрешений без элементов после удаления" {
            makerClient.getAllMakers().size shouldBe 0
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        makerRepository.deleteAll()
        roleRepository.deleteAll()
    }
}
