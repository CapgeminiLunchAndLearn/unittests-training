package com.programmaniaks.training.unittest.exceptions;

public class NotEnoughtQtyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6559315919692099878L;
	
	public NotEnoughtQtyException(String message){
		super(message);
	}
	
	public NotEnoughtQtyException(){
		super();
	}

}
