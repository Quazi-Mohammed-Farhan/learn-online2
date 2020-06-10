package com.learn.online.exceptions;

import com.learn.online.utils.GeneralResponseBody;

public class CourseToPurchaseDoesNotExistsClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private GeneralResponseBody generalResponseBody;
	
	public CourseToPurchaseDoesNotExistsClientSideException(String message, 
			GeneralResponseBody generalResponseBody) {
		super(message);
		
		this.generalResponseBody = generalResponseBody;
	}

	public GeneralResponseBody getGeneralResponseBody() {
		return generalResponseBody;
	}

	public void setGeneralResponseBody(GeneralResponseBody generalResponseBody) {
		this.generalResponseBody = generalResponseBody;
	}
	
}
