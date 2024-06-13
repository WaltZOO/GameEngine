package ai;

import model.Entity;

public class Disjonction {

    // CONSTRUCTOR
    public Disjonction(Condition c1, Condition c2) {
        this.cond1 = c1;
        this.cond2 = c2;
    }

    // FIELDS
    Condition cond1, cond2;

    // REQUIRED BY INTERFACE
    public boolean eval(Entity e) {
        return cond1.eval(e) || cond2.eval(e);
    }
}
