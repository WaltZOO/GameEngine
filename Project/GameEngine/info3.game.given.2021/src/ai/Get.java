package ai;

import model.Entity;

public class Get implements Action {

    @Override
    public void exec(Entity e) {
        e.do_get();
    }

}
