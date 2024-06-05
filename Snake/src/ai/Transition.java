package ai;

public class Transition {
    State source, cible;
    Condition c;
    Action a;

    public Transition(State source, State cible, Condition c, Action a) {
        this.source = source;
        this.cible = cible;
        this.c = c;
        this.a = a;
    }
}