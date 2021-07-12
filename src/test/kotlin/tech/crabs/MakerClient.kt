package tech.crabs

import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.crabs.panel.maker.MakerCreate
import tech.crabs.panel.maker.MakerInfo

@Client(value = "/makers")
interface MakerClient {

    @Get
    fun getAllMakers(): List<MakerInfo>

    @Post
    fun addMaker(maker: MakerCreate): MakerInfo

    @Delete(value = "/{code}")
    fun deleteMakerByCode(code: String): MakerInfo
}
