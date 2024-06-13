package ai;

import java.util.ArrayList;
import java.util.Random;

import model.Entity;

interface StepByStep {
	public void step(Entity e);
}

public class FSM implements StepByStep {
	ArrayList<Transition> instructions;

	State start;

	public FSM(State start) {
		this.start = start;
		this.instructions = new ArrayList<Transition>();
	}

	public void add_transition(Transition t) {
		instructions.add(t);
	}

	public void step(Entity e) // generique a tous les automates (<30 lignes)
	{
		if (instructions.isEmpty()) {
			return;
		}

		int i = 0;

		// | pas depasser taille ! | bon etat de depart ! | condition respectée ! |
		while (i < instructions.size() && (!instructions.get(i).getSource().equals(e.getState())
				|| !instructions.get(i).getCondition().eval(e))) {
			i++;
		}
		if (instructions.get(i).getTarget() == null) {
			return;
		}
		e.setState(instructions.get(i).getTarget());

		for (ActionProba act : instructions.get(i).getAction()) {
			Random rand = new Random();
			int randomNum = rand.nextInt(100) + 1;
			int proba_cumul = 0;
			for (int j = 0; j < act.proba.size(); j++) {
				proba_cumul += act.proba.get(j);
				if (proba_cumul > randomNum) {
					act.a.get(j).exec(e);
					break;
				}
			}

		}

	}

}
