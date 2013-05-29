package com.raltamirano.fsm.conditions;

import com.raltamirano.fsm.Condition;
import com.raltamirano.fsm.Context;

public class AlwaysTrue implements Condition {

	private static final AlwaysTrue INSTANCE = new AlwaysTrue();
	
	private AlwaysTrue() {}
	
	public static AlwaysTrue getInstance() {
		return INSTANCE;
	}
	
	public boolean evaluate(Context context) {
		return true;
	}
}
