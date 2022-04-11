package eandradep.io.geojsonproject.models.entity

import com.google.cloud.firestore.GeoPoint
import java.io.Serializable

class Point : Serializable {

    constructor(geoPoint: GeoPoint?){
        latitudePoint = geoPoint!!.latitude
        longitudePoint = geoPoint.longitude
    }

    constructor()

    var latitudePoint: Double = 0.0
    var longitudePoint: Double = 0.0
}