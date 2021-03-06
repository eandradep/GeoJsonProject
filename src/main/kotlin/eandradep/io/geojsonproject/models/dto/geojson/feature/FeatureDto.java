package eandradep.io.geojsonproject.models.dto.geojson.feature;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectDto;
import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.GeometryDto;

/**
 * @author moksuzer
 *
 */
public class FeatureDto extends GeoJSONObjectDto {

	private static final long serialVersionUID = 1L;

	private GeometryDto geometry;
	private String properties;
	private String id;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Feature;
	}

	public GeometryDto getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryDto geometry) {
		this.geometry = geometry;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
