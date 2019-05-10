package com.chubb.dashboard;

public class Result {
	
	String functionality;
	String action;
	boolean status;
	String message;
	String exception;
	
	public String getFunctionality() {
		return functionality;
	}
	
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}		
}