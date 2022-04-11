package eandradep.io.geojsonproject.models.logic.exception;


import eandradep.io.geojsonproject.models.dto.geojson.PositionDto;

public class InvalidPositionDtoException extends RuntimeException {

	public InvalidPositionDtoException(PositionDto position) {
		super("Invalid position dto: " + position);
	}

}
