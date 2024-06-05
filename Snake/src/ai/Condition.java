package ai;

import model.Entity;

public interface Condition {

    public boolean eval(Entity e){
        return e.do_eval();
    }

}
