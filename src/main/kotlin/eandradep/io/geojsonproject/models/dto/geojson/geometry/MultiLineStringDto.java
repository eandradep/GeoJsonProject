package eandradep.io.geojsonproject.models.dto.geojson.geometry;



import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class MultiLineStringDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<LineStringDto> lines;

	public List<LineStringDto> getLines() {
		return lines;
	}

	public void setLines(List<LineStringDto> lines) {
		this.lines = lines;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiLineString;
	}
}
