package ai;

import java.util.ArrayList;

import model.Entity;

public class Automate {
	ArrayList<Transition> instructions;

	public Automate() {
		instructions = new ArrayList<Transition>();
	}

	public void add_transition(Transition t) {
		instructions.add(t);
	}

	void step(Entity e) // generique a tous les automates (<30 lignes)
	{
		System.out.print("NYI");
	}

}
