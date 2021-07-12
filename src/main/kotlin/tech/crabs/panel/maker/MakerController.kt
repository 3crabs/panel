package tech.crabs.panel.maker

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller(value = "/makers")
@Tag(name = "Создатели")
class MakerController {

    @Inject
    lateinit var makerService: MakerService

    @Get
    @Operation(summary = "Получение создателей")
    fun getAllPermissions() = makerService.getAllMakers()

    @Post
    @Operation(summary = "Создание создателя")
    fun addPermission(maker: MakerCreate) = makerService.addMaker(maker)

    @Delete(value = "/{code}")
    @Operation(summary = "Удаление создателя")
    fun deletePermissionByCode(code: String) = makerService.deleteMakerByCode(code)
}
