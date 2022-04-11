package eandradep.io.geojsonproject.models.service.coordinates

import eandradep.io.geojsonproject.models.dto.PathPassengerData
import eandradep.io.geojsonproject.models.entity.RouteCoordinatePoint

interface ICoordinatesService {

    fun getAllRouteGraphicsPoints(pathPassengerData: PathPassengerData): List<RouteCoordinatePoint>?

}