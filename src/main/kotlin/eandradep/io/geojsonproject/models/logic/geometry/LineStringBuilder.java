package eandradep.io.geojsonproject.models.logic.geometry;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.LineStringDto;
import eandradep.io.geojsonproject.models.logic.common.BuilderConstants;

/**
 * @author moksuzer
 *
 */
public class LineStringBuilder extends GeometryBuilder<LineStringDto> {

	private static final LineStringBuilder INSTANCE = new LineStringBuilder();

	public static LineStringBuilder getInstance() {
		return INSTANCE;
	}

	private LineStringBuilder() {
	}

	@Override
	public String toGeoJSON(LineStringDto lineString) {
		if (lineString == null || lineString.getPositions() == null || lineString.getPositions().isEmpty()) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.LineString);

		builder.append(BuilderConstants.COORDINATES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);
		builder.append(BuilderConstants.NEWLINE);

		buildLineStringPositions(builder, lineString);

		builder.append(BuilderConstants.CLOSE_BRACKET);

		buildBbox(builder, lineString.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
