package com.raltamirano.fsm.actions;

import com.raltamirano.fsm.Action;
import com.raltamirano.fsm.Context;

public class ErrorAction implements Action {

	private final String message;
	
	private ErrorAction(String message) {
		this.message = message;
	}
	
	public static ErrorAction withMessage(String message) {
		return new ErrorAction(message);
	}
	
	public void execute(Context context) {
		System.err.println(message);
		context.dump();
	}
}
