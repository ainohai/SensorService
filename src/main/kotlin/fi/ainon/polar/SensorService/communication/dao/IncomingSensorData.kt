package fi.ainon.polar.SensorService.communication.dao

import java.io.Serializable

data class SensorData (val type: String, val byteArr: ByteArray): Serializable