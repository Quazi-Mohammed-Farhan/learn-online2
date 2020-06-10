package com.learn.online.exceptions;

import com.learn.online.utils.GeneralResponseBody;

public class CourseToCancelPurchaseDoesNotExistsClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private GeneralResponseBody generalResponseBody;
	
	public CourseToCancelPurchaseDoesNotExistsClientSideException(String message, 
			GeneralResponseBody generalResponseBody) {
		super(message);
		
		this.generalResponseBody = generalResponseBody;
	}
	
}
