package tech.crabs.roles

/**
 * роль пользователя внешней системы
 */
data class RoleCreate(

    /**
     * код роли
     * уникально
     */
    val code: String,

    /**
     * название роли
     * уникально
     */
    val name: String
)
