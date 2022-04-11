package eandradep.io.geojsonproject.models.logic.feature;

import eandradep.io.geojsonproject.models.dto.geojson.GeoJSONObjectTypeEnum;
import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureCollectionDto;
import eandradep.io.geojsonproject.models.dto.geojson.feature.FeatureDto;
import eandradep.io.geojsonproject.models.logic.common.BuilderConstants;
import eandradep.io.geojsonproject.models.logic.common.GeoJSONBuilder;

import java.util.List;

/**
 * Build FeatureCollectionDto object to GeoJSON String
 * 
 * @author moksuzer
 *
 */
public class FeatureCollectionBuilder extends GeoJSONBuilder<FeatureCollectionDto> {

	private static final FeatureCollectionBuilder INSTANCE = new FeatureCollectionBuilder();

	private FeatureCollectionBuilder() {
	}

	public static FeatureCollectionBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public String toGeoJSON(FeatureCollectionDto featureCollection) {
		if (featureCollection == null) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.FeatureCollection);

		builder.append(BuilderConstants.FEATURES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);

		List<FeatureDto> features = featureCollection.getFeatures();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				String featureGeoJSON = FeatureBuilder.getInstance().toGeoJSON(features.get(i));
				builder.append(featureGeoJSON);
				if (i < features.size() - 1) {
					builder.append(BuilderConstants.COMMA_NEWLINE);
				}
			}
		}

		builder.append(BuilderConstants.CLOSE_BRACKET);

		buildBbox(builder, featureCollection.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
