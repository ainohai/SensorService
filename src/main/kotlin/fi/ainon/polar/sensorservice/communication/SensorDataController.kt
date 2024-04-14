package fi.ainon.polar.sensorservice.communication

import fi.ainon.polar.sensorservice.communication.dao.SensorData
import fi.ainon.polar.sensorservice.logic.SensorDataHandler
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class SensorDataController(private val sensorDataHandler: SensorDataHandler) {

    @GetMapping("/")
    fun index() : String {
        System.out.println("Called root")
        return "pong"
    }

    @PostMapping("/addData")
    fun addData(@RequestBody request: SensorData) : HttpStatus {
        System.out.println("Called data")
        sensorDataHandler.addData(request)
        return HttpStatus.OK
    }
}
