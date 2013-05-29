package com.raltamirano.fsm;

import com.raltamirano.fsm.exceptions.FSMException;

public final class TransitionRule {
	private final State sourceState;
	private final Condition condition;
	private final State targetState;

	public TransitionRule(State sourceState, Condition condition, State targetState) {
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
}
