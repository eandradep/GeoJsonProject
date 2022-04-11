package eandradep.io.geojsonproject.models.logic.geometry;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.PointDto;
import eandradep.io.geojsonproject.models.logic.common.BuilderConstants;

/**
 * @author moksuzer
 *
 */
public class PointBuilder extends GeometryBuilder<PointDto> {

	private static final PointBuilder INSTANCE = new PointBuilder();

	public static PointBuilder getInstance() {
		return INSTANCE;
	}

	private PointBuilder() {
	}

	@Override
	public String toGeoJSON(PointDto point) {
		if (point == null || point.getPosition() == null || point.getPosition().getNumbers().length == 0) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.Point);

		builder.append(BuilderConstants.COORDINATES_SPACE);
		builder.append(PositionBuilder.getInstance().position(point.getPosition()));

		buildBbox(builder, point.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
