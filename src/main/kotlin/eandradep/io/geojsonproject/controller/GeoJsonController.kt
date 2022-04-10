package eandradep.io.geojsonproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/geoJson")
class GeoJsonController {

    @GetMapping("/generate")
    fun generateGeoJson(
    ): Any {
        val response = HashMap<String, Any>()
        return try {
            run {
                response["message"] = "USER CONTROLLER HAVE BEEN FOUND IN THE DATA BASE"
                response["result"] = "HOLA MUNDO"
                ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
            }
        } catch (e: Exception) {
            response["message"] = "SERVER ERROR ...!!!!"
            response["error"] = "${e.message}"
            ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}