package tech.crabs.panel

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("tech.crabs")
        .start()
}
