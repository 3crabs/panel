package tech.crabs

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("tech.crabs")
        .start()
}

