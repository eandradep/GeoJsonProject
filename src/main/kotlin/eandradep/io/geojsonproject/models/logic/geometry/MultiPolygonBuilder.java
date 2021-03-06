package eandradep.io.geojsonproject.models.logic.geometry;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.LineStringDto;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.MultiPolygonDto;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.PolygonDto;
import eandradep.io.geojsonproject.models.logic.common.BuilderConstants;

import java.util.List;

/**
 * @author moksuzer
 *
 */
public class MultiPolygonBuilder extends GeometryBuilder<MultiPolygonDto> {

	private static final MultiPolygonBuilder INSTANCE = new MultiPolygonBuilder();

	public static MultiPolygonBuilder getInstance() {
		return INSTANCE;
	}

	private MultiPolygonBuilder() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ugeojson.builder.geometry.GeometryBuilder#toGeometryGeoJSON(com
	 * .erumi.ugeojson.model.geometry.GeometryDto)
	 */
	@Override
	public String toGeoJSON(MultiPolygonDto multiPolygon) {
		if (multiPolygon == null || multiPolygon.getPolygons() == null || multiPolygon.getPolygons().isEmpty()) {
			return BuilderConstants.NULL_VALUE;
		}

		List<PolygonDto> polygons = multiPolygon.getPolygons();
		for (PolygonDto polygonDto : polygons) {
			checkAndCorrectLinearRing(polygonDto);
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.MultiPolygon);

		builder.append(BuilderConstants.COORDINATES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);
		builder.append(BuilderConstants.NEWLINE);

		for (int k = 0; k < polygons.size(); k++) {
			PolygonDto polygonDto = polygons.get(k);
			List<LineStringDto> linearRings = polygonDto.getLinearRings();

			builder.append(BuilderConstants.OPEN_BRACKET);
			builder.append(BuilderConstants.NEWLINE);

			for (int i = 0; i < linearRings.size(); i++) {
				builder.append(BuilderConstants.OPEN_BRACKET);
				builder.append(BuilderConstants.NEWLINE);

				buildLineStringPositions(builder, linearRings.get(i));

				builder.append(BuilderConstants.CLOSE_BRACKET);
				if (i < linearRings.size() - 1) {
					builder.append(BuilderConstants.COMMA_NEWLINE);
				} else {
					builder.append(BuilderConstants.NEWLINE);
				}
			}

			builder.append(BuilderConstants.CLOSE_BRACKET);
			if (k < polygons.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}

		}

		builder.append(BuilderConstants.CLOSE_BRACKET);

		buildBbox(builder, multiPolygon.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
