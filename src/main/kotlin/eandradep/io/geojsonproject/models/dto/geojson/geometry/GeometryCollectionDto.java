package eandradep.io.geojsonproject.models.dto.geojson.geometry;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class GeometryCollectionDto extends GeometryDto {

	private List<GeometryDto> geometries;

	public List<GeometryDto> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<GeometryDto> geometries) {
		this.geometries = geometries;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.GeometryCollection;
	}
}
