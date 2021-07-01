package tech.crabs

import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.function.FunctionCreate
import tech.crabs.panel.function.FunctionInfo
import tech.crabs.panel.function.FunctionUpdate

@Client(value = "/functions")
interface FunctionClient {

    @Get
    fun getAllFunctions(): List<FunctionInfo>

    @Post
    fun createFunction(function: FunctionCreate): FunctionInfo

    @Get(value = "/{code}")
    fun getFunctionByCode(code: String): FunctionInfo

    @Put(value = "/{code}")
    fun updateFunction(code: String, function: FunctionUpdate): FunctionInfo

    @Delete(value = "/{code}")
    fun deleteFunctionByCode(code: String): FunctionInfo
}
