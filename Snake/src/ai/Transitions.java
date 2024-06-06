package ai;

import java.util.ArrayList;

public class Transitions {
	ArrayList<Transition> instructions;
	
	public Transitions() {
		instructions = new ArrayList<Transition>();
	}
	
	public void add(Transition t) {
		instructions.add(t);
	}
}
