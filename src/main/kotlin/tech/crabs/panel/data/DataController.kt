package tech.crabs.panel.data

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller(value = "/data")
@Tag(name = "Информация")
class DataController {

    @Inject
    lateinit var dataService: DataService

    @Get
    @Operation(summary = "Получение информации")
    fun getData() = dataService.getData()

    @Post
    @Operation(summary = "Загрузка информации")
    fun loadData(data: Data) = dataService.loadData(data)
}
