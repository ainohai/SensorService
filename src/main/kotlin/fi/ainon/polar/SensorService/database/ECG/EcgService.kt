package fi.ainon.polar.SensorService.database.ECG

import fi.ainon.polar.SensorService.communication.dao.EcgData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Repository
interface EcgRepository: JpaRepository<EcgEntity, Long> {

}

interface  EcgService {
    fun addEcg(ecgData: EcgData?): MutableList<EcgEntity>
}


@Service
class JPAEcgService (private val ecgRepository: EcgRepository): EcgService {

    override fun addEcg(ecgData: EcgData?): MutableList<EcgEntity> {

        val datapoints : MutableList<EcgEntity> = mutableListOf()
        ecgData?.samples?.forEach { ecgValue ->
            datapoints.add(EcgEntity(ecgValue.timeStamp, ecgValue.voltage))
        }

        return ecgRepository.saveAll(datapoints)
    }
}