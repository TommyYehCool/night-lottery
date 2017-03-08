package com.ingeniousinc.lottery.util.exception;

public class HttpUtilException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HttpUtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpUtilException(String message) {
		super(message);
	}
	
}
