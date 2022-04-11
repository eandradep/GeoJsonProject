package eandradep.io.geojsonproject.models.service.generategeojson

import eandradep.io.geojsonproject.models.entity.RouteCoordinatePoint

interface IGeoJsonService {

    fun geoJsonGenerator(routeCoordinatePointList :List<RouteCoordinatePoint>, uuid: String): String?
}