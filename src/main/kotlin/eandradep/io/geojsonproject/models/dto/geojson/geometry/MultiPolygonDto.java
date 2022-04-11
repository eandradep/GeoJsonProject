package eandradep.io.geojsonproject.models.dto.geojson.geometry;



import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class MultiPolygonDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PolygonDto> polygons;

	public List<PolygonDto> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<PolygonDto> polygons) {
		this.polygons = polygons;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPolygon;
	}
}
