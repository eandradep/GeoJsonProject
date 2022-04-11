package eandradep.io.geojsonproject.models.logic;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectDto;
import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureCollectionDto;
import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureDto;
import eandradep.io.geojsonproject.models.dto.geojson.geometry.*;
import eandradep.io.geojsonproject.models.logic.common.BuilderConstants;
import eandradep.io.geojsonproject.models.logic.feature.FeatureBuilder;
import eandradep.io.geojsonproject.models.logic.feature.FeatureCollectionBuilder;
import eandradep.io.geojsonproject.models.logic.geometry.*;

/**
 * @author moksuzer
 *
 */
public class UltimateGeoJSONBuilder {

	private static final UltimateGeoJSONBuilder INSTANCE = new UltimateGeoJSONBuilder();

	public static UltimateGeoJSONBuilder getInstance() {
		return INSTANCE;
	}

	private UltimateGeoJSONBuilder() {
	}

	public String toGeoJSON(GeoJSONObjectDto geoJsonObjectDto) {
		if (geoJsonObjectDto == null) {
			return BuilderConstants.NULL_VALUE;
		}

		if (geoJsonObjectDto instanceof PointDto) {
			return PointBuilder.getInstance().toGeoJSON((PointDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof LineStringDto) {
			return LineStringBuilder.getInstance().toGeoJSON((LineStringDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof PolygonDto) {
			return PolygonBuilder.getInstance().toGeoJSON((PolygonDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof FeatureDto) {
			return FeatureBuilder.getInstance().toGeoJSON((FeatureDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof FeatureCollectionDto) {
			return FeatureCollectionBuilder.getInstance().toGeoJSON((FeatureCollectionDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiPointDto) {
			return MultiPointBuilder.getInstance().toGeoJSON((MultiPointDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiLineStringDto) {
			return MultiLineStringBuilder.getInstance().toGeoJSON((MultiLineStringDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiPolygonDto) {
			return MultiPolygonBuilder.getInstance().toGeoJSON((MultiPolygonDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof GeometryCollectionDto) {
			return GeometryCollectionBuilder.getInstance().toGeoJSON((GeometryCollectionDto) geoJsonObjectDto);
		}

		return BuilderConstants.NULL_VALUE;
	}

}
