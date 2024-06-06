package ai;

import model.Entity;
import ai.Condition;

public interface Condition {
    public boolean eval(Entity e);

}

class Conjonction implements Condition {

    // CONSTRUCTOR
    public Conjonction(Condition c1, Condition c2){
        this.cond1 = c1;
        this.cond2 = c2;
    }

    // FIELDS
    Condition cond1, cond2;
    
    // REQUIRED BY INTERFACE
    public boolean eval(Entity e){
        return cond1.eval(e) && cond2.eval(e);
    }
}