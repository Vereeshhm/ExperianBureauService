package com.example.Experian.Bureau.Service.Exception;

public class EmptyPanException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyPanException(String message)
	{
		super(message);
	}

}
