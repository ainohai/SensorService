package fi.ainon.polar.sensorservice.logic

import fi.ainon.polar.sensorservice.database.DataService
import fi.ainon.polar.sensorservice.communication.dao.EcgData
import fi.ainon.polar.sensorservice.communication.dao.SensorData
import fi.ainon.polarAppis.worker.dataObject.AccData
import fi.ainon.polarAppis.worker.dataObject.DataType
import fi.ainon.polarAppis.worker.dataObject.HrData
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component

interface SensorDataHandler {
    fun addData(data: SensorData)
}

@Component
class DefaultSensorDataHandler(private val dataService: DataService) : SensorDataHandler {
    override fun addData(data: SensorData) {

        val dataType = data.type;
        val byteArrToString = data.byteArr.toString(Charsets.UTF_16)

        when (dataType) {
            DataType.ECG.name -> ecg(byteArrToString)
            DataType.ACC.name -> acc(byteArrToString)
            DataType.HR.name -> hr(byteArrToString)
            else -> throw IllegalArgumentException("Datatype $dataType is not allowed")
        }
    }

    private fun ecg(data: String) {

        val ecgData = Json.decodeFromString<EcgData>(data)

        ecgData.samples.forEach { data ->
            println("ECG: yV: ${data.voltage} timeStamp: ${data.timeStamp}")
        }

        dataService.addEcg(ecgData)
    }

    private fun acc(data: String) {
        var accData = Json.decodeFromString<AccData>(data)
        accData.samples.forEach { data ->
            println("ACC collected  x: ${data.x} y: ${data.y} z: ${data.z} timeStamp: ${data.timeStamp}")
        }
        // Doing currently nothing for the data.
    }

    private fun hr(data: String) {
        var hrData = Json.decodeFromString<HrData>(data)
        hrData.samples.forEach { data ->
            println("HR collected  bpm: ${data.hr} rrs: ${data.rrsMs} rrAvailable: ${data.rrAvailable} contactStatus: ${data.contactStatus} contactStatusSupported: ${data.contactStatusSupported}")
        }
        dataService.addHr(hrData)
    }
}