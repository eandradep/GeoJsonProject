package eandradep.io.geojsonproject.models.dto.geojson;


import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureCollectionDto;
import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureDto;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.*;

/**
 * GeoJSON object types
 * 
 * @author moksuzer
 *
 */
public enum GeoJSONObjectTypeEnum {

	Point(PointDto.class), MultiPoint(MultiPointDto.class), LineString(LineStringDto.class), MultiLineString(
			MultiLineStringDto.class), Polygon(PolygonDto.class), MultiPolygon(
					MultiPolygonDto.class), GeometryCollection(GeometryCollectionDto.class), Feature(
							FeatureDto.class), FeatureCollection(FeatureCollectionDto.class);

	private final Class dtoClass;

	private GeoJSONObjectTypeEnum(Class dtoClass) {
		this.dtoClass = dtoClass;
	}

	public Class getDtoClass() {
		return dtoClass;
	}
}
