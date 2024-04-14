package fi.ainon.polar.sensorservice.communication.dao

import java.io.Serializable

data class SensorData (val type: String, val byteArr: ByteArray): Serializable