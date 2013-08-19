package com.raltamirano.fsm;

import java.util.ArrayList;
import java.util.List;

import com.raltamirano.fsm.exceptions.FSMException;

public final class Transition {
	private final State sourceState;
	private final Condition condition;
	private final State targetState;
	private final List<Action> actions;

	public Transition(State sourceState, Condition condition, State targetState) {
		if (sourceState == null) {
			throw new FSMException("'sourceState' can not be null!");
		}
		if (condition == null) {
			throw new FSMException("'condition' can not be null!");
		}
		if (targetState == null) {
			throw new FSMException("'targetState' can not be null!");
		}
				
		this.sourceState = sourceState;
		this.condition = condition;
		this.targetState = targetState;
		this.actions = new ArrayList<Action>();
	}

	public Transition(State sourceState, Condition condition, State targetState, Action action) {
		this(sourceState, condition, targetState);

		if (action == null) {
			throw new FSMException("'action' can not be null!");
		}
		
		this.actions.add(action);
	}
	
	public State getSourceState() {
		return this.sourceState;
	}

	public Condition getCondition() {
		return this.condition;
	}

	public State getTargetState() {
		return this.targetState;
	}
	
	public List<Action> getActions() {
		return this.actions;
	}
}
