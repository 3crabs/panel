package tech.crabs.panel.function

import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller(value = "/functions")
@Tag(name = "Функциии")
class FunctionController {

    @Inject
    lateinit var functionService: FunctionService

    @Get
    @Operation(summary = "Получение функций")
    fun getAllFunctions() = functionService.getAllFunctions()

    @Post
    @Operation(summary = "Создание функциии")
    fun createFunction(function: FunctionCreate) = functionService.createFunction(function)

    @Get(value = "/{code}")
    @Operation(summary = "Получение функциии по коду")
    fun getFunctionByCode(code: String): FunctionInfo = functionService.getFunctionByCode(code)

    @Put(value = "/{code}")
    @Operation(summary = "Редактирование функциии по коду")
    fun updateFunction(code: String, function: FunctionUpdate) =
        functionService.updateFunction(code, function)

    @Delete(value = "/{code}")
    @Operation(summary = "Удаление функциии по коду")
    fun deleteFunctionByCode(code: String) = functionService.deleteFunctionByCode(code)
}
