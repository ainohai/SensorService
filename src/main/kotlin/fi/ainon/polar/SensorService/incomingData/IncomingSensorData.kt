package fi.ainon.polar.SensorService.incomingData

import java.io.Serializable

data class SensorData (val type: String, val byteArr: ByteArray): Serializable