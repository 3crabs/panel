package tech.crabs.panel

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "panel",
        version = "0.4",
        description = "Вспомогательное ПО для контроля разрешений пользователей с различными ролями на доступ к той или иной функциональности основного ПО.",
        contact = Contact(
            url = "https://github.com/FedorovVladimir",
            name = "Fedorov Vladimir",
            email = "vladimir.fodorow@gmail.com"
        )
    )
)
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(Application.javaClass)
    }
}
