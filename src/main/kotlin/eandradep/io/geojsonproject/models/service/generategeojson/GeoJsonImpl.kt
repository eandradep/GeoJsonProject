package eandradep.io.geojsonproject.models.service.generategeojson

import eandradep.io.geojsonproject.models.dto.geojson.PositionDto
import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureDto
import eandradep.io.geojsonproject.models.dto.geojson.geometry.LineStringDto
import eandradep.io.geojsonproject.models.entity.RouteCoordinatePoint
import eandradep.io.geojsonproject.models.logic.UltimateGeoJSONBuilder
import org.springframework.stereotype.Service

@Service
class GeoJsonImpl : IGeoJsonService {

    override fun geoJsonGenerator(routeCoordinatePointList: List<RouteCoordinatePoint>, uuid: String): String? {
        val feature = FeatureDto()
        val contraventionDTOList = arrayListOf<PositionDto>()
        for (routeCoordinatePoint in routeCoordinatePointList){
            val positionDto = PositionDto(
                routeCoordinatePoint.point.longitudePoint,
                routeCoordinatePoint.point.latitudePoint)
            contraventionDTOList.add(positionDto)
        }
        val pathPassengerRouteGraph = LineStringDto(contraventionDTOList)
        feature.geometry = pathPassengerRouteGraph
        feature.id = "\"$uuid\""
        feature.properties = "{\"stroke\": \"#1c71d8\",\n" +
                "        \"stroke-width\": 1.7,\n" +
                "        \"stroke-opacity\": 1,\n" +
                "        \"color\": \"#F7455D\"}"
        return UltimateGeoJSONBuilder.getInstance().toGeoJSON(feature)
    }
}