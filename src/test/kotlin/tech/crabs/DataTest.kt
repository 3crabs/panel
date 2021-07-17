package tech.crabs

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.data.Data
import tech.crabs.panel.function.FunctionInfo
import tech.crabs.panel.function.FunctionRepository
import tech.crabs.panel.maker.MakerInfo
import tech.crabs.panel.maker.MakerRepository
import tech.crabs.panel.permission.PermissionInfo
import tech.crabs.panel.permission.PermissionRepository
import tech.crabs.panel.roles.RoleInfo
import tech.crabs.panel.roles.RoleRepository
import java.time.LocalDateTime
import javax.inject.Inject

@MicronautTest
class DataTest : StringSpec() {

    @Inject
    lateinit var dataClient: DataClient

    @Inject
    lateinit var makerRepository: MakerRepository

    @Inject
    lateinit var permissionRepository: PermissionRepository

    @Inject
    lateinit var functionRepository: FunctionRepository

    @Inject
    lateinit var roleRepository: RoleRepository

    init {
        "Загрузка без информации" {
            dataClient.loadData(
                Data(
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                )
            )
            val data = dataClient.getData()
            data.roles.size shouldBe 0
            data.functions.size shouldBe 0
            data.permissions.size shouldBe 0
            data.makers.size shouldBe 0
        }

        "Создание роли" {
            dataClient.loadData(
                Data(
                    listOf(RoleInfo(code = "code", name = "name", created = LocalDateTime.now())),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                )
            )
            val data = dataClient.getData()
            data.roles.size shouldBe 1
            data.functions.size shouldBe 0
            data.permissions.size shouldBe 0
            data.makers.size shouldBe 0
        }

        "Создание функции" {
            dataClient.loadData(
                Data(
                    emptyList(),
                    listOf(FunctionInfo(code = "code", name = "name", created = LocalDateTime.now())),
                    emptyList(),
                    emptyList(),
                )
            )
            val data = dataClient.getData()
            data.roles.size shouldBe 0
            data.functions.size shouldBe 1
            data.permissions.size shouldBe 0
            data.makers.size shouldBe 0
        }

        "Создание разрешения" {
            dataClient.loadData(
                Data(
                    listOf(RoleInfo(code = "code", name = "name", created = LocalDateTime.now())),
                    listOf(FunctionInfo(code = "code", name = "name", created = LocalDateTime.now())),
                    listOf(
                        PermissionInfo(
                            code = "code__code",
                            created = LocalDateTime.now(),
                            functionCode = "code",
                            roleCode = "code"
                        )
                    ),
                    emptyList(),
                )
            )
            val data = dataClient.getData()
            data.roles.size shouldBe 1
            data.functions.size shouldBe 1
            data.permissions.size shouldBe 1
            data.makers.size shouldBe 0
        }

        "Создание создателя" {
            dataClient.loadData(
                Data(
                    listOf(RoleInfo(code = "code", name = "name", created = LocalDateTime.now())),
                    emptyList(),
                    emptyList(),
                    listOf(
                        MakerInfo(
                            code = "code__code",
                            created = LocalDateTime.now(),
                            roleCodeWho = "code",
                            roleCodeWhom = "code"
                        )
                    ),
                )
            )
            val data = dataClient.getData()
            data.roles.size shouldBe 1
            data.functions.size shouldBe 0
            data.permissions.size shouldBe 0
            data.makers.size shouldBe 1
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        makerRepository.deleteAll()
        permissionRepository.deleteAll()
        functionRepository.deleteAll()
        roleRepository.deleteAll()
    }
}
