package fi.ainon.polar.SensorService.communication

import fi.ainon.polar.SensorService.incomingData.SensorData
import fi.ainon.polar.SensorService.logic.SensorDataHandler
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
        sensorDataHandler.addEcgData(request)
        return HttpStatus.OK
    }
}
