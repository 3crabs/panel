package tech.crabs.panel.maker

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import tech.crabs.panel.roles.RoleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakerService {

    @Inject
    lateinit var roleRepository: RoleRepository

    @Inject
    lateinit var makerRepository: MakerRepository

    @Inject
    lateinit var makerConverter: MakerConverter

    fun getAllMakers(): List<MakerInfo> {
        return makerRepository.findAll().map { makerConverter.convert(it) }
    }

    fun addMaker(maker: MakerCreate): MakerInfo {
        roleRepository.findByCode(maker.roleCodeWho) ?: throw badRequest("role not found")
        roleRepository.findByCode(maker.roleCodeWhom) ?: throw badRequest("role not found")
        return makerConverter.convert(makerRepository.save(makerConverter.convert(maker)))
    }

    fun addMakers(makers: List<MakerInfo>): List<MakerInfo> {
        if (makers.isEmpty()) {
            return emptyList()
        }
        return makerRepository.saveAll(makers.map { makerConverter.convert(it) }).map { makerConverter.convert(it) }
    }

    fun deleteMakerByCode(code: String): MakerInfo {
        val p = makerRepository.findByCode(code) ?: throw badRequest("marker not found")
        makerRepository.delete(p)
        return makerConverter.convert(p)
    }

    fun deleteAllMarkers() {
        makerRepository.deleteAll()
    }

    private fun badRequest(message: String) = HttpStatusException(HttpStatus.BAD_REQUEST, message)
}
