/*
 *-------------------------------------------------------------------
 *
 * Safeway Corporation (c) 2011
 *
 *-------------------------------------------------------------------
 */
package com.safeway.emclclient.emcl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {	
	private String code;
	private String message;
	private String fieldName;

	
	public Error(String errorCode, String message, String fieldName) {
		this.code = errorCode;
		this.message = message;
		this.fieldName = fieldName;
	}
	
	public Error() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	@Override
	public String toString() {
		return "Error [code=" + code + ", message=" + message + ", fieldName="
				+ fieldName + "]";
	}

	
}
