package eandradep.io.geojsonproject.models.service.coordinates

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QuerySnapshot
import eandradep.io.geojsonproject.models.dto.PathPassengerData
import eandradep.io.geojsonproject.models.entity.RouteCoordinatePoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoordinatesImpl : ICoordinatesService {

    @Autowired
    private val firestore: Firestore? = null

    private val historial: String= "historial"
    private val fechas: String= "fechas"
    private val puntos: String= "puntos"

    @Throws(Exception::class)
    override fun getAllRouteGraphicsPoints(pathPassengerData: PathPassengerData): List<RouteCoordinatePoint>?  {
        val contraventionDTOList = arrayListOf<RouteCoordinatePoint>()
        val result: ApiFuture<QuerySnapshot> = firestore!!
            .collection(pathPassengerData.cityName)
            .document("historial_"+pathPassengerData.cityName)
            .collection(historial)
            .document(pathPassengerData.pathPassengerRoute)
            .collection(fechas)
            .document(pathPassengerData.documentIdentification)
            .collection(puntos).get()
        for (queryResult in result.get()){
            contraventionDTOList.add(RouteCoordinatePoint(queryResult))
        }
        return contraventionDTOList
    }

}