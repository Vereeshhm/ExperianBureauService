package com.example.Experian.Bureau.Service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidPhonenumException.class)
	public ResponseEntity<String> handleInvalidPhonenumException(InvalidPhonenumException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"Invalid phoneNumber\", " + "\"type\": \"Bad Request\", " + "\"statusCode\": 400, "
				+ "\"name\": \"error\"" + "}}";
		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(PhonnumRequiredException.class)
	public ResponseEntity<String> handlePhonnumRequiredException(PhonnumRequiredException ex) {
		// Create a custom response body
		String responseBody = "{" + "\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"\\\"phoneNumber\\\" must be a number\", " + "\"type\": \"Bad Request\", "
				+ "\"statusCode\": 400, " + "\"name\": \"error\"" + "}" + "}";
		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(InvalidParameterException.class)

	public ResponseEntity<String> handleInvalidParameterException(InvalidParameterException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": {" + "\"reason\": \"ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"Invalid/missing input parameter\", " + "\"type\": \"Bad Request\", "
				+ "\"statusCode\": 400, " + "\"name\": \"error\"" + "}}";
		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(EmptyDobException.class)
	public ResponseEntity<String> handleEmptyDobException(EmptyDobException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"\\\"dateOfBirth\\\" is not allowed to be empty\", " + "\"type\": \"Bad Request\", "
				+ "\"statusCode\": 400, " + "\"name\": \"error\"" + "}}";
		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(DobformatException.class)

	public ResponseEntity<String> handleDobformatException(DobformatException ex) {
		String responseBody = String.format("{" + "\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", "
				+ "\"status\": 400, "
				+ "\"message\": \"\\\"dateOfBirth\\\" with value \\\"%s\\\" fails to match the required pattern: /^\\\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/\", "
				+ "\"type\": \"Bad Request\", " + "\"statusCode\": 400, " + "\"name\": \"error\"" + "}" + "}",
				ex.getDateOfBirth());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(EmptyPanException.class)

	public ResponseEntity<String> handleEmptyPanException(EmptyPanException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"\\\"pan\\\" is not allowed to be empty\", " + "\"type\": \"Bad Request\", "
				+ "\"statusCode\": 400, " + "\"name\": \"error\"" + "}}";
		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(Emptyfirstnameexception.class)
	public ResponseEntity<String> handleEmptyfirstnameexception(Emptyfirstnameexception ex) {
		// Create a custom response body
		String responseBody = "{\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"\\\"firstName\\\" is not allowed to be empty\", " + "\"type\": \"Bad Request\", "
				+ "\"statusCode\": 400, " + "\"name\": \"error\"" + "}}";
		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(EmptylastnameException.class)

	public ResponseEntity<String> handleEmptylastnameException(EmptylastnameException ex) {
		
		String responseBody = "{\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"\\\"lastName\\\" is not allowed to be empty\", " + "\"type\": \"Bad Request\", "
				+ "\"statusCode\": 400, " + "\"name\": \"error\"" + "}}";
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(pincoderequireexception.class)

	public ResponseEntity<String> handlepincoderequireexception(pincoderequireexception ex) {
		
		String responseBody = "{" + "\"error\": {" + "\"reason\": \"VALIDATION_ERROR\", " + "\"status\": 400, "
				+ "\"message\": \"Invalid pincode\", " + "\"type\": \"Bad Request\", " + "\"statusCode\": 400, "
				+ "\"name\": \"error\"" + "}" + "}";
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}
	
	@ExceptionHandler(NotfoundException.class)

	public ResponseEntity<String> handleNotfoundException(NotfoundException ex) {
		
		String responseBody = "{" + "\"error\": {" + "\"reason\": \"ERROR\", " + "\"status\": 404, "
				+ "\"message\": \"No Record Found\", " + "\"type\": \"Not Found\", " + "\"statusCode\": 404, "
				+ "\"name\": \"error\"" + "}" + "}";
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
	}
	
	
	

}
