package fi.ainon.polar.SensorService.incomingData

import java.io.Serializable

@kotlinx.serialization.Serializable
data class EcgData(
    val samples: List<EcgDataSample>,

    ) : Serializable {
    /**
     *  @property timeStamp moment sample is taken in nanoseconds. The epoch of timestamp is 1.1.2000
     *  @property voltage ECG in microVolts.
     */
    @kotlinx.serialization.Serializable
    data class EcgDataSample(
        val timeStamp: Long,
        val voltage: Int
    ) : Serializable
}