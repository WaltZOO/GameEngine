package ai;

import model.Entity;

public class True implements Condition {
	
	public True() {
	}

	@Override
	public boolean eval(Entity e) {
		return true;
	}
	

}
