package ai;

import model.Entity;

public interface Condition {
public boolean eval(Entity e);
}

class Cell implements Condition {
	int dir;
	int cat;
	
	public Cell(int dir, int cat) {
		this.dir = dir;
		this.cat = cat;
	}

	@Override
	public boolean eval(Entity e) {
		return true;
		//return e.eval_cell(dir, cat);
	}
	
}
