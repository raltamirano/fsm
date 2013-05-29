package com.raltamirano.fsm.conditions;

import com.raltamirano.fsm.Condition;
import com.raltamirano.fsm.Context;

public class VariableValueExpected implements Condition {

	private final String variable;
	private final Object expectedValue;
	
	public VariableValueExpected(String variable, Object expectedValue) {
		this.variable = variable;
		this.expectedValue = expectedValue;
	}
	
	public boolean evaluate(Context context) {
		Object variableValue = context.get(this.variable);		
		return variableValue == null ? false : variableValue.equals(this.expectedValue);
	}
}
