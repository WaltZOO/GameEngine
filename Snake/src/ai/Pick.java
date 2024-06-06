package ai;

import model.Entity;

public class Pick implements Action {

    @Override
    public void exec(Entity e) {
        e.do_pick();
    }
}
