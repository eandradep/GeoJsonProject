package eandradep.io.geojsonproject.models.logic.geometry;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.PositionDto;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.MultiPointDto;
import eandradep.io.geojsonproject.models.logic.common.BuilderConstants;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class MultiPointBuilder extends GeometryBuilder<MultiPointDto> {

	private static final MultiPointBuilder INSTANCE = new MultiPointBuilder();

	public static MultiPointBuilder getInstance() {
		return INSTANCE;
	}

	private MultiPointBuilder() {
	}

	@Override
	public String toGeoJSON(MultiPointDto multiPoint) {
		if (multiPoint == null || multiPoint.getPositions() == null || multiPoint.getPositions().isEmpty()) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.MultiPoint);
		builder.append(BuilderConstants.COORDINATES_SPACE);

		builder.append(BuilderConstants.OPEN_BRACKET);
		builder.append(BuilderConstants.NEWLINE);

		List<PositionDto> positions = multiPoint.getPositions();
		for (int i = 0; i < positions.size(); i++) {
			PositionDto position = positions.get(i);
			builder.append(PositionBuilder.getInstance().position(position));
			if (i < positions.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}
		}

		builder.append(BuilderConstants.CLOSE_BRACKET);
		
		buildBbox(builder, multiPoint.getBbox());
		endBuilder(builder);
		return builder.toString();
	}

}
