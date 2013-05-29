package com.raltamirano.fsm.conditions;

import com.raltamirano.fsm.Condition;
import com.raltamirano.fsm.Context;

public class VariableValueNotInDomain implements Condition {

	private final String variable;
	private final Object[] domain;
	
	public VariableValueNotInDomain(String variable, Object[] domain) {
		this.variable = variable;
		this.domain = domain;
	}
	
	public boolean evaluate(Context context) {
		Object variableValue = context.get(this.variable);
		
		if (variableValue == null) {
			return false;
		}
		
		for(Object value : domain) {
			if (variableValue.equals(value)) {
				return false;
			}
		}

		return true;
	}
}
