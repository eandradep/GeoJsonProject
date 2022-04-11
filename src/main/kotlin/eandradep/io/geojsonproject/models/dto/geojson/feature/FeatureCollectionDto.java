package eandradep.io.geojsonproject.models.dto.geojson.feature;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectDto;
import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class FeatureCollectionDto extends GeoJSONObjectDto {
 
	private static final long serialVersionUID = 1L;
	
	private List<FeatureDto> features;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.FeatureCollection;
	}

	public List<FeatureDto> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureDto> features) {
		this.features = features;
	}
	
}
