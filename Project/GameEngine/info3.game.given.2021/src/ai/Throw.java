package ai;

import model.Entity;

public class Throw implements Action {
	String direction;
	String category;

    public Throw(String direction, String category) {
        this.direction = direction;
        this.category = category;
    }

    public Throw() {
    }

    @Override
    public void exec(Entity e) {
       e.do_throw(direction, category);
    }

}
