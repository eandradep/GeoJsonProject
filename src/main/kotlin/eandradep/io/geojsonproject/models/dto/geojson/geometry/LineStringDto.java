package eandradep.io.geojsonproject.models.dto.geojson.geometry;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.PositionDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author moksuzer
 *
 */
public class LineStringDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PositionDto> positions;

	public LineStringDto(){
	}
	
	public LineStringDto(List<PositionDto> positions){
		this.positions = new ArrayList<>();
		for (PositionDto positionDto : positions) {
			this.positions.add(new PositionDto(positionDto));
		}
	}

	public List<PositionDto> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionDto> positions) {
		this.positions = positions;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.LineString;
	}
}
