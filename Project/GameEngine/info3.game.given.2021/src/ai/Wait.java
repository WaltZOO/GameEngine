package ai;

import model.Entity;

public class Wait implements Action {
    @Override
    public void exec(Entity e) {
        e.do_wait();
    }
}
