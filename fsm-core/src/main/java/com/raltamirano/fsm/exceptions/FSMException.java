package com.raltamirano.fsm.exceptions;

public class FSMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FSMException(String message) {
		super(message);
	}

	public FSMException(String message, Throwable cause) {
		super(message, cause);
	}
}
