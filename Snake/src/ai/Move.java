package ai;

import model.Entity;

public class Move implements Action {

    int direction;

    public Move(int direction) {
        this.direction = direction;
    }

    @Override
	public void exec(Entity e) {
        e.do_move(direction);
    }
}