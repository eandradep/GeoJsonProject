package eandradep.io.geojsonproject.controller

import eandradep.io.geojsonproject.models.dto.PathPassengerData
import eandradep.io.geojsonproject.models.entity.RouteCoordinatePoint
import eandradep.io.geojsonproject.models.service.coordinates.ICoordinatesService
import eandradep.io.geojsonproject.models.service.gcpconfig.GcpConfiguration
import eandradep.io.geojsonproject.models.service.generategeojson.IGeoJsonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*


@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/geoJson")
class GeoJsonController {

    @Autowired
    private val iCoordinatesService: ICoordinatesService? = null

    @Autowired
    private val iGeoJsonService: IGeoJsonService? = null

    //    /CUENCA/historial_CUENCA/historial/ruta_54_path_14436/fechas/08-04-2022:05:41:43/puntos/08-04-2022:05:41:50

    @GetMapping("/generate")
    fun generateGeoJson(
    ): Any {
        val response = HashMap<String, Any>()
        return try {
            val pathPassengerData = PathPassengerData(
                "CUENCA",
                "ruta_54_path_14436",
                "08-04-2022:05:41:43"
            )
            val contraventionDTOList = iCoordinatesService?.getAllRouteGraphicsPoints(pathPassengerData)
            if (contraventionDTOList!!.isEmpty()) {
                response["message"] = "PATH PASSENGER ROUTE INFORMATION NOT FOUND"
                response["result"] = arrayListOf<RouteCoordinatePoint>()
                return ResponseEntity<Map<*, *>>(response, HttpStatus.NOT_FOUND)
            }

            val uuid = UUID.randomUUID()

            val geJsonData: String = iGeoJsonService!!.geoJsonGenerator(contraventionDTOList, uuid.toString())!!
            if (geJsonData.isEmpty()){
                response["message"] = "PATH PASSENGER ROUTE INFORMATION NOT FOUND"
                response["result"] = arrayListOf<RouteCoordinatePoint>()
                return ResponseEntity<Map<*, *>>(response, HttpStatus.NOT_FOUND)
            }

            try {
                val ruta = "/home/edisonandrade/Escritorio/$uuid.json"
                val file = File(ruta)
                // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile()
                }
                val fw = FileWriter(file)
                val bw = BufferedWriter(fw)
                bw.write(geJsonData)
                bw.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            val gcpConfiguration = GcpConfiguration()
            gcpConfiguration.testUploadFile();
            response["message"] = "USER CONTROLLER HAVE BEEN FOUND IN THE DATA BASE"
            response["result"] = geJsonData
            return ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
        } catch (e: Exception) {
            response["message"] = "SERVER ERROR ...!!!!"
            response["error"] = "${e.message}"
            ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}