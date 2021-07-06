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
import tech.crabs.panel.function.FunctionCreate
import tech.crabs.panel.function.FunctionRepository
import tech.crabs.panel.function.FunctionUpdate
import javax.inject.Inject

@MicronautTest
class FunctionTest : StringSpec() {

    @Inject
    lateinit var functionClient: FunctionClient

    @Inject
    lateinit var functionRepository: FunctionRepository

    init {
        "Получение списка функциий без элементов" {
            functionClient.getAllFunctions().size shouldBe 0
        }

        "Создание функциии code 1" {
            val f = functionClient.createFunction(FunctionCreate(" code 1 ", " name 1 "))
            f.name shouldBe "name 1"
            f.code shouldBe "code 1"
            f.created.shouldNotBeNull()
        }

        "Создание функциии code 2" {
            val f = functionClient.createFunction(FunctionCreate(" code 2 ", " name 2 "))
            f.name shouldBe "name 2"
            f.code shouldBe "code 2"
            f.created.shouldNotBeNull()
        }

        "Попытка создание функциии с кодом который уже существует заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                functionClient.createFunction(FunctionCreate(" code 1 ", "name"))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("code is already in use")
        }

        "Попытка создание функциии с именем которое уже существует заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                functionClient.createFunction(FunctionCreate("code", " name 1 "))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message.shouldContain("name is already in use")
        }

        "Получение списка функциий с 2 элементами" {
            functionClient.getAllFunctions().size shouldBe 2
        }

        "Получение функциии по коду" {
            val f = functionClient.getFunctionByCode("code 1")
            f.name shouldBe "name 1"
            f.code shouldBe "code 1"
            f.created.shouldNotBeNull()
        }

        "Попытка получение функциии по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                functionClient.getFunctionByCode("bad_code")
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "function not found"
        }

        "Редактирование функциии" {
            var f = functionClient.updateFunction("code 1", FunctionUpdate(" name 1 new "))
            f.name shouldBe "name 1 new"
            f.code shouldBe "code 1"
            f.created.shouldNotBeNull()
            f = functionClient.getFunctionByCode("code 1")
            f.name shouldBe "name 1 new"
            f.code shouldBe "code 1"
            f.created.shouldNotBeNull()
        }

        "Попытка редактирования функциии по коду которого нет в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                functionClient.updateFunction("bad_code", FunctionUpdate("name"))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "function not found"
        }

        "Попытка присвоения функциии имени которое уже есть в базе заканчивается ошибкой" {
            val e = shouldThrow<HttpClientResponseException> {
                functionClient.updateFunction("code 2", FunctionUpdate(" name 1 new "))
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "name is already in use"
        }

        "Присвоение функциии своего же имени" {
            val f = functionClient.updateFunction("code 2", FunctionUpdate(" name 2 "))
            f.name shouldBe "name 2"
            f.code shouldBe "code 2"
            f.created.shouldNotBeNull()
        }

        "Удаление функциии code 1"{
            val f = functionClient.deleteFunctionByCode("code 1")
            f.name shouldBe "name 1 new"
            f.code shouldBe "code 1"
            f.created.shouldNotBeNull()
        }

        "Попытка удаление функциии по коду которого нет в базе заканчивается ошибкой"{
            val e = shouldThrow<HttpClientResponseException> {
                functionClient.deleteFunctionByCode("bad_code")
            }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "function not found"
        }

        "Получение списка функциий с 1 элементом"{
            functionClient.getAllFunctions().size shouldBe 1
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        functionRepository.deleteAll()
    }
}
