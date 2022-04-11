package eandradep.io.geojsonproject.models.dto.geojson.geometry;



import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.PositionDto;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class MultiPointDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PositionDto> positions;
 
	public List<PositionDto> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionDto> positions) {
		this.positions = positions;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPoint;
	}
}
