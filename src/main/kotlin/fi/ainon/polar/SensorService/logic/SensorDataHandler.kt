package fi.ainon.polar.SensorService.logic

import fi.ainon.polar.SensorService.database.ECG.EcgService
import fi.ainon.polar.SensorService.incomingData.EcgData
import fi.ainon.polar.SensorService.incomingData.SensorData
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component

interface SensorDataHandler {
    fun addEcgData(data: SensorData)
}

@Component
class DefaultSensorDataHandler(private val ecgService: EcgService) : SensorDataHandler {
    override fun addEcgData(data: SensorData) {

        val byteArrToString = data.byteArr.toString(Charsets.UTF_16)
        val ecgData = Json.decodeFromString<EcgData>(byteArrToString)

        ecgData.samples.forEach { data ->
            println("    yV: ${data.voltage} timeStamp: ${data.timeStamp}")
        }

        ecgService.addEcg(ecgData)
    }
}