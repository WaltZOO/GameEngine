package ai;

import model.Entity;

public class Cell implements Condition {
    Direction dir;
    Category cat;

	@Override
	public boolean eval(ai.Entity e) {
		return e.eval_cell(dir, cat);
	}
}