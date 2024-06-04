import java.util.ArrayList;

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
        int i = 0;
        // on cherche la transition qui correspond a l'etat courant et qui a une moto
        // | pas depasser taille ! | bon etat de depart ! | condition respectée ! |
        while (i < instructions.size() && (instructions.get(i).getSource() != e.getState()
                || !instructions.get(i).getCondition().eval(e))) {
            i++;
        }
        instructions.get(i).getAction().exec(e);
        e.setState(instructions.get(i).getTarget());

    }

}
