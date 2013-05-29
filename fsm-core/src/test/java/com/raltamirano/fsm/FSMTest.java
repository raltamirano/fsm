package com.raltamirano.fsm;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.raltamirano.fsm.conditions.AlwaysTrue;
import com.raltamirano.fsm.conditions.VariableValueExpected;
import com.raltamirano.fsm.conditions.VariableValueNotInDomain;

public class FSMTest 
    extends TestCase
{
	public FSMTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( FSMTest.class );
    }

    public void testSampleUsage()
    {
    	// Input are modeled as context variables of the state machine. Here is an input called "input1".
    	// The desired action/input (i.e.: "open", "close") is set in this variable.
    	final String INPUT1 = "input1";
    	
		// States for this FSM
		State openDoor = new State("open");
		State closedDoor = new State("closed");
		State errorState = new State("error", 
				new Action() {					
					public void execute(Context context) {
						System.err.println("Oops! An error has occured!");
						context.dump();
					}
				}, null);
		
		List<State> states = new ArrayList<State>();
		states.add(openDoor);
		states.add(closedDoor);
		states.add(errorState);

		// State transition table
		List<TransitionRule> stateTransitionTable = new ArrayList<TransitionRule>();
		stateTransitionTable.add(new TransitionRule(openDoor, new VariableValueExpected(INPUT1, "open"), openDoor));
		stateTransitionTable.add(new TransitionRule(openDoor, new VariableValueExpected(INPUT1, "close"), closedDoor));
		stateTransitionTable.add(new TransitionRule(openDoor, new VariableValueNotInDomain(INPUT1, new Object[] {"open", "close"}), errorState));
		stateTransitionTable.add(new TransitionRule(closedDoor, new VariableValueExpected(INPUT1, "open"), openDoor));
		stateTransitionTable.add(new TransitionRule(closedDoor, new VariableValueExpected(INPUT1, "close"), closedDoor));
		stateTransitionTable.add(new TransitionRule(closedDoor, new VariableValueNotInDomain(INPUT1, new Object[] {"open", "close"}), errorState));
		stateTransitionTable.add(new TransitionRule(errorState, AlwaysTrue.getInstance(), errorState));
		
		// Create the FSM for a door, with 'closed' as the initial state.
		FSM doorFSM = new FSM(states, stateTransitionTable, closedDoor);
				
		// Check initial state.
		Assert.assertEquals("Initial state should be 'closed'",  
				"closed", doorFSM.getCurrentState().toString());
		
		// For the rest of the assertions, please check the state transition table defined above. 
		
		// "close" on a closed door must leave the door still closed
		doorFSM.getContext().set(INPUT1, "close");
		doorFSM.evaluate();
		Assert.assertEquals("closeOnClosedDoor", "closed", doorFSM.getCurrentState().toString());
		
		// "open" on a closed door must open the door!
		doorFSM.getContext().set(INPUT1, "open");
		doorFSM.evaluate();
		Assert.assertEquals("openOnClosedDoor", "open", doorFSM.getCurrentState().toString());

		// "open" on an open door must leave the door still open
		doorFSM.getContext().set(INPUT1, "open");
		doorFSM.evaluate();
		Assert.assertEquals("openOnOpenDoor", "open", doorFSM.getCurrentState().toString());

		// "close" on a open door must close the door!
		doorFSM.getContext().set(INPUT1, "close");
		doorFSM.evaluate();
		Assert.assertEquals("closeOnOpenDoor", "closed", doorFSM.getCurrentState().toString());
		
		// other values for "input1" must put the FSM on the error state
		doorFSM.getContext().set(INPUT1, "unknown");
		doorFSM.evaluate();
		Assert.assertEquals("invalidInput", "error", doorFSM.getCurrentState().toString());		
    }
}
