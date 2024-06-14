package com.example.Experian.Bureau.Service.Exception;

public class DobformatException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 private final String dateOfBirth;

	    public DobformatException(String dateOfBirth) {
	        super(String.format("\"dateOfBirth\" with value \"%s\" fails to match the required pattern: /^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/", dateOfBirth));
	        this.dateOfBirth = dateOfBirth;
	    }

	    public String getDateOfBirth() {
	        return dateOfBirth;
	    }
}
