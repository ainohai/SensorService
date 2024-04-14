package fi.ainon.polar.sensorservice.database

import fi.ainon.polar.sensorservice.communication.dao.EcgData
import fi.ainon.polarAppis.worker.dataObject.HrData
import org.springframework.stereotype.Service


interface  DataService {
    fun addEcg(ecgData: EcgData?): MutableList<EcgEntity>
    fun addHr(hrData: HrData?): MutableList<HrEntity>
}


@Service
class JPADataService (private val ecgRepository: EcgRepository, private val hrRepository: HrRepository): DataService {

    override fun addEcg(ecgData: EcgData?): MutableList<EcgEntity> {

        val datapoints : MutableList<EcgEntity> = mutableListOf()
        ecgData?.samples?.forEach { ecgValue ->
            datapoints.add(EcgEntity(ecgValue.timeStamp, ecgValue.voltage))
        }

        return ecgRepository.saveAll(datapoints)
    }

    override fun addHr(hrData: HrData?): MutableList<HrEntity> {

        val datapoints : MutableList<HrEntity> = mutableListOf()
        hrData?.samples?.forEach { hrValue ->
            datapoints.add(HrEntity(hrValue.hr, hrValue.rrsMs))
        }

        return hrRepository.saveAll(datapoints)
    }
}