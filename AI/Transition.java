public class Transition {
    State source, target;
    Condition c;
    Action a;

    public Transition(State source, State target, Condition c, Action a) {
        this.source = source;
        this.target = target;
        this.c = c;
        this.a = a;
    }

    State getSource() {
        return this.source;
    }

    void setSource(State s) {
        this.source = s;
    }

    Condition getCondition() {
        return this.c;
    }

    Action getAction() {
        return this.a;
    }

    State getTarget() {
        return this.target;
    }

    void setCondition(Condition cond) {
        this.c = cond;
    }
}
