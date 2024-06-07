package ai;

import java.util.ArrayList;
// import model.Entity;

public class Transition {
    State source, target;
    Condition c;
    ArrayList<ActionProba> a;

    public Transition(State source, State target, Condition c, ArrayList<ActionProba> act) {
        this.source = source;
        this.target = target;
        this.c = c;
        this.a = act;
    }

    public Condition getCondition() {
        return c;
    }

    public State getSource() {
        return source;
    }

    public State getTarget() {
        return target;
    }

    public ArrayList<ActionProba> getAction() {
        return a;
    }
    



}