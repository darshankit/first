package com.kit.erp.entity.auth;

public class LoginResponse {
	private String status;
	private String statusCode;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public LoginResponse(String status, String statusCode) {
		super();
		this.status = status;
		this.statusCode = statusCode;
	}
	public LoginResponse() {
		super();
	}
	
	
}
