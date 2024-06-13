package ai;

import model.Entity;

public class Turn implements Action {

    String direction;

    public Turn(String direction) {
        this.direction = direction;
    }

    // tourne dans le sens horaire
    public Turn() {

    }

    @Override
    public void exec(Entity e) {
        e.do_turn(direction);
    }
}
