package com.learn.online.enums;

public enum HeaderNamesAndValues {
	
	USER_AGENT_NAME("user-agent"), 
	
	USER_AGENT_HEADER_VALUES("Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
		+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

	private String headerValues;
	
	private HeaderNamesAndValues(String headerValues) {
		this.headerValues = headerValues;
	}
	
	public String getHeaderValues() {
		return headerValues;
	}
	
	public String getHeaderName() {
		return headerValues;
	}
}	


