package eandradep.io.geojsonproject.models.entity

import com.google.cloud.firestore.QueryDocumentSnapshot
import java.io.Serializable

class RouteCoordinatePoint : Serializable {

    constructor()

    @Throws(NullPointerException::class)
    constructor(querySnapshot: QueryDocumentSnapshot?){
        dia = querySnapshot!!.getString("dia")!!
        hora = querySnapshot.getString("hora")!!
        fechaActual = querySnapshot.getDate("fechaActual")!!.time
        internetConnected = querySnapshot.getBoolean("internetConnected")!!
        precision = querySnapshot.getDouble("presicion") as Double
        point = Point(querySnapshot.getGeoPoint("ubicacion"))
    }

    var dia: String = ""
    var hora: String = ""
    var fechaActual: Long = 0L
    var internetConnected: Boolean = true
    var precision: Double = 0.0
    var point: Point = Point()


}