package com.chubb.selenium.applications;

public class Status {
	boolean validation;
	String exception;
	String message;
	
	public boolean isValidation() {
		return validation;
	}
	
	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
