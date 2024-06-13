package ai;

import model.Entity;

public class Pick implements Action {

	String direction;

	public Pick(String direction) {
		this.direction = direction;
	}

	public Pick() {

	}

	@Override
	public void exec(Entity e) {
		e.do_pick(direction);
	}
}
