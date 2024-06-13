package ai;

import model.Entity;

public class Move implements Action {

    String direction;

    public Move(String direction2) {
        this.direction = direction2;
    }
    public Move() {
    }

    @Override
	public void exec(Entity e) {
        e.do_move(direction);
    }
}