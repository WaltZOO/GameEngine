package ai;

import model.Entity;

public class Store implements Action {

    @Override
    public void exec(Entity e) {
        e.do_store();
    }

}
