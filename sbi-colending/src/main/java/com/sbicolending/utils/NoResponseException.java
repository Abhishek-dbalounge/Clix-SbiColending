package com.sbicolending.utils;

import com.sbicolending.exception.BaseException;

public class NoResponseException  extends BaseException
{
	private static final long serialVersionUID = 6942649118798917750L;
	private String errorCode;
	private String errorDesc; 
	
	public NoResponseException() {
		super();
	}
	
	public NoResponseException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
	
	public NoResponseException(String errorCode, String errorDesc) {
		
		this.errorCode = errorCode;
		this.errorDesc= errorDesc;
	}
	
	public NoResponseException(String message, Throwable cause) {
	        super(message, cause);
	}
	
	public NoResponseException(Throwable cause) {
        super(cause);
    }
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMsg() {
		return errorDesc;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorDesc = errorMsg;
	}
}
