package tech.crabs.panel.function

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FunctionService {

    @Inject
    lateinit var functionConverter: FunctionConverter

    @Inject
    lateinit var functionRepository: FunctionRepository

    fun getAllFunctions(): List<FunctionInfo> =
        functionRepository.findAll().map { functionConverter.convert(it) }

    fun addFunction(function: FunctionCreate): FunctionInfo {
        functionRepository.findByCode(function.code.trim())?.let { throw badRequest("code is already in use") }
        functionRepository.findByName(function.name.trim())?.let { throw badRequest("name is already in use") }
        return functionConverter.convert(functionRepository.save(functionConverter.convert(function)))
    }

    fun addFunctions(functions: List<FunctionInfo>): List<FunctionInfo> {
        if (functions.isEmpty()) {
            return emptyList()
        }
        return functionRepository.saveAll(functions.map { functionConverter.convert(it) })
            .map { functionConverter.convert(it) }
    }

    fun getFunctionByCode(code: String): FunctionInfo {
        val p = functionRepository.findByCode(code) ?: throw badRequest("function not found")
        return functionConverter.convert(p)
    }

    fun updateFunction(code: String, function: FunctionUpdate): FunctionInfo {
        val r = functionRepository.findByCode(code) ?: throw badRequest("function not found")
        val name = function.name.trim()
        functionRepository.findByCodeNotEqualAndName(code, name)?.let { throw badRequest("name is already in use") }
        r.name = name
        return functionConverter.convert(functionRepository.update(r))
    }

    fun deleteFunctionByCode(code: String): FunctionInfo {
        val r = functionRepository.findByCode(code) ?: throw badRequest("function not found")
        functionRepository.delete(r)
        return functionConverter.convert(r)
    }

    fun deleteAllFunctions() {
        functionRepository.deleteAll()
    }

    private fun badRequest(message: String) = HttpStatusException(HttpStatus.BAD_REQUEST, message)
}
