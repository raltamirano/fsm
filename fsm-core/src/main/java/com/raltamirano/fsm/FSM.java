package com.raltamirano.fsm;

import java.util.ArrayList;
import java.util.List;

import com.raltamirano.fsm.exceptions.FSMException;

public final class FSM {
	private static final String CURRENT_STATE_CONTEXT_KEY = "FSM_CURRENT_STATE";
	private static final String PREVIOUS_STATE_CONTEXT_KEY = "FSM_PREVIOUS_STATE";
	
	private List<State> states;
	private Context context;
	private List<TransitionRule> transitionTable;
		
	public FSM(List<State> states, List<TransitionRule> rules, State initialState) {
		this(states, rules, initialState, null);
	}

	public FSM(List<State> states, List<TransitionRule> rules, State initialState, Context context) {
		// Validations
		if (states == null || states.size() == 0) {
			throw new FSMException("'states' list can not be neither null nor empty!");
		}

		if (rules == null || rules.size() == 0) {
			throw new FSMException("'rules' list can not be neither null nor empty!");
		}
				
		if (initialState == null) {
			throw new FSMException("'initialState' list can not be null!");
		}
		
		// Initialization		
		this.context = new Context();
		if (context != null) {
			this.context.importContext(context);
		}

		this.context.set(PREVIOUS_STATE_CONTEXT_KEY, null);			
		this.context.set(CURRENT_STATE_CONTEXT_KEY, initialState);			
		
		this.states = new ArrayList<State>();
		for(State state : states) {
			this.states.add(state);
		}		
		
		this.transitionTable = new ArrayList<TransitionRule>();
		for(TransitionRule rule : rules) {
			this.transitionTable.add(rule);
		}				
	}
	
	public State getCurrentState() {
		return (State)this.context.get(CURRENT_STATE_CONTEXT_KEY);
	}

	public Context getContext() {
		return this.context;
	}

	public void evaluate() {
		List<TransitionRule> compatibleRules = new ArrayList<TransitionRule>();
		
		for(TransitionRule rule : this.transitionTable) {
			if (rule.getSourceState() == this.getCurrentState()) {
				if (rule.getCondition().evaluate(this.context)) {
					compatibleRules.add(rule);
				}
			}
		}

		if (compatibleRules.size() == 0) {
			throw new FSMException("No target state found for the current state/machine context!");
		} else if (compatibleRules.size() > 1) {
			throw new FSMException("More than one target states found for the current state/machine context!");
		} else {
			TransitionRule ruleFound = compatibleRules.get(0);
			executeAction(this.getCurrentState().getExitAction());					
			this.context.set(PREVIOUS_STATE_CONTEXT_KEY, this.getCurrentState());
			this.context.set(CURRENT_STATE_CONTEXT_KEY, ruleFound.getTargetState());
			executeAction(ruleFound.getTargetState().getEntryAction());									
		}
 	}
	
	private void executeAction(Action action) {
		try {
			if (action != null) {
				action.execute(this.context);
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}
