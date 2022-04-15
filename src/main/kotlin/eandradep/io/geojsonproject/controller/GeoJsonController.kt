package eandradep.io.geojsonproject.controller

import eandradep.io.geojsonproject.models.dto.PathPassengerData
import eandradep.io.geojsonproject.models.entity.RouteCoordinatePoint
import eandradep.io.geojsonproject.models.service.coordinates.ICoordinatesService
import eandradep.io.geojsonproject.models.service.gcpconfig.IGcpUploadService
import eandradep.io.geojsonproject.models.service.generategeojson.IGeoJsonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/geoJson")
class GeoJsonController {

    @Autowired
    private val iCoordinatesService: ICoordinatesService? = null

    @Autowired
    private val iGeoJsonService: IGeoJsonService? = null

    @Autowired
    private val iGcpUploadService: IGcpUploadService? = null

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

            val fileUUID = UUID.randomUUID()
            val fileName= "$fileUUID.json"
            val filePath = "../$fileName"

            val geJsonData: String = iGeoJsonService!!.geoJsonGenerator(contraventionDTOList, fileUUID.toString())!!
            if (geJsonData.isEmpty()){
                response["message"] = "PATH PASSENGER ROUTE INFORMATION NOT FOUND"
                response["result"] = arrayListOf<RouteCoordinatePoint>()
                return ResponseEntity<Map<*, *>>(response, HttpStatus.NOT_FOUND)
            }
            val urlFile: String = iGcpUploadService!!.uploadFileGcpStorage(filePath, fileName, geJsonData)!!
            if (urlFile.isEmpty()){
                response["message"] = "ERROR UPLOADING DATA !!!"
                response["result"] = arrayListOf<RouteCoordinatePoint>()
                return ResponseEntity<Map<*, *>>(response, HttpStatus.NOT_FOUND)
            }
            response["message"] = "DATA IS SUCCESSFULLY MIGRATE"
            response["result"] = urlFile
            return ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
        } catch (e: Exception) {
            response["message"] = "SERVER ERROR ...!!!!"
            response["error"] = "${e.message}"
            ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}