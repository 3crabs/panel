package tech.crabs

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.panel.permission.PermissionRepository
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
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        permissionRepository.deleteAll()
    }
}
