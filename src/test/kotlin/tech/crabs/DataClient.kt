package tech.crabs

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.data.Data

@Client(value = "/data")
interface DataClient {

    @Get
    fun getData(): Data

    @Post
    fun loadData(data: Data): Data
}
