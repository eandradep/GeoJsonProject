package eandradep.io.geojsonproject.models.logic.exception;


import eandradep.io.geojsonproject.models.dto.geojson.geometry.PolygonDto;

public class InvalidPolygonDtoException extends RuntimeException {
 

	private final PolygonDto polygon;

	public InvalidPolygonDtoException(PolygonDto polygon) {
		super("Invalid polygon dto: " + polygon);
		this.polygon = polygon;
	}
	
	public PolygonDto getPolygon() {
		return polygon;
	}

}
