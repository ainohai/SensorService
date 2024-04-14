package fi.ainon.polar.sensorservice.database

import jakarta.persistence.*


@Entity
@Table(name="hr")
class HrEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1
    val hr: Int
    val rrsMs: String

    constructor() {
        hr = 0
        rrsMs = ""
    }

    constructor(hr: Int, rrsMs: List<Int>) {
        this.hr = hr
        this.rrsMs = rrsMs.joinToString()
    }
}