package com.raltamirano.fsm;

import com.raltamirano.fsm.exceptions.FSMException;

public class State {
	private final String name;
	private Action entryAction;
	private Action exitAction;

	public State(String name) {
		if (name == null) {
			throw new FSMException("'name' can not be null!");
		}
		
		this.name = name;
	}

	public State(String name, Action entryAction, Action exitAction) {
		this(name);
		
		this.entryAction = entryAction;
		this.exitAction = exitAction;
	}
	
	public String getName() {
		return this.name;
	}

	public Action getEntryAction() {
		return this.entryAction;
	}

	public Action getExitAction() {
		return this.exitAction;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((State)obj).name);
	}
}
