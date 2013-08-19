package com.raltamirano.fsm;

import java.util.ArrayList;
import java.util.List;

import com.raltamirano.fsm.exceptions.FSMException;

public class State {
	private final String name;
	private final List<Action> entryActions;
	private final List<Action> exitActions;

	public State(String name) {
		if (name == null) {
			throw new FSMException("'name' can not be null!");
		}
		
		this.name = name;
		this.entryActions = new ArrayList<Action>();
		this.exitActions = new ArrayList<Action>();
	}

	public State(String name, Action entryAction, Action exitAction) {
		this(name);
		
		if (entryAction != null)
			this.entryActions.add(entryAction);
		
		if (exitAction != null)
			this.exitActions.add(exitAction);
	}
	
	public String getName() {
		return this.name;
	}

	public List<Action> getEntryActions() {
		return this.entryActions;
	}

	public List<Action> getExitActions() {
		return this.exitActions;
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
