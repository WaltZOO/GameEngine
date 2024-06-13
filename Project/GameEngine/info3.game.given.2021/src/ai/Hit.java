package ai;

import model.Entity;

public class Hit implements Action {

    String direction;

    public Hit(String direction) {
        this.direction = direction;
    }

    public Hit() {

    }

    @Override
    public void exec(Entity e) {
        e.do_hit(direction);
    }
}
