package fi.ainon.polar.SensorService.database.ECG

import jakarta.persistence.*


@Entity
@Table(name="ecg")
class EcgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1
    val timepoint: Long
    val voltage: Int

    constructor() {
        this.timepoint = 0
        this.voltage = 0
    }

    constructor(timeStamp: Long, voltage: Int) {
        this.timepoint = timeStamp
        this.voltage = voltage
    }
}