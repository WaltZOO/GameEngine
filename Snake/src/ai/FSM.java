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
		if (instructions.isEmpty()) return;
        int i = 0;		
	
        // | pas depasser taille ! | bon etat de depart ! | condition respectée ! |
		Transition current = instructions.get(i);
        while (i < instructions.size() -1  && (current.getSource() != e.getState()
                || !current.getCondition().eval(e))) {
            i++;
			current = instructions.get(i);
        }
		e.setState(current.getTarget());
        for (Action act: current.getAction()) {
			act.exec(e);
		}
        

    }

}
