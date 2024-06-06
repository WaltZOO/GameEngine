package ai;

import java.util.ArrayList;
import model.Entity;


interface StepByStep{
	public void step(Entity e);
}

public class FSM implements StepByStep{
	ArrayList<Transition>  instructions;
	
	public FSM() {
		this.instructions = new ArrayList<Transition>();	
	}
	public void add_transition(Transition t) {
		instructions.add(t);
	}
	public void step(Entity e) // generique a tous les automates (<30 lignes)
    {
        int i = 0;
	
        // | pas depasser taille ! | bon etat de depart ! | condition respectée ! |
        while (i < instructions.size() && (instructions.get(i).getSource() != e.getState()
                || !instructions.get(i).getCondition().eval(e))) {
            i++;
        }
		e.setState(instructions.get(i).getTarget());
        for (Action act: instructions.get(i).getAction()) {
			act.exec(e);
		}
        

    }

}
