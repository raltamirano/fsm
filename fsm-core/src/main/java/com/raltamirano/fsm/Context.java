package com.raltamirano.fsm;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.raltamirano.fsm.exceptions.FSMException;

public final class Context {
	private Map<String, Object> variables;
	
	public Context() {
		this.variables = new HashMap<String, Object>();
	}
	
	public Object get(String variable) {
		return this.variables.get(variable);
	}

	public void set(String variable, Object value) {
		if (variable == null) {
			throw new FSMException("'variable' can not be null!");
		}
		
		this.variables.put(variable, value);
	}
	
	public void importContext(Context context) {
		for(String variable : context.variables.keySet()) {
			this.set(variable, context.get(variable));
		}
	}

	public void dump() {
		dump(System.err);
	}
	
	public void dump(OutputStream outputStream) {
		PrintStream printStream = new PrintStream(outputStream);
		printStream.println("Context dump:");
		for(String variable : this.variables.keySet()) {
			printStream.println(String.format("\t[%s]=[%s]", variable, this.variables.get(variable)));			
		}
	}
}
