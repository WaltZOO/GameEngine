package ai;

import model.Entity;

public interface Action {
	public void exec(Entity e);
	
}

class Move implements Action {
	
	@Override
    public void exec(Entity e) {
        e.do_move(e.getDir());
    }
}

class Turn implements Action {
	
	@Override
    public void exec(Entity e) {
        e.do_turn(e.getDir());
    }
}

class Pick implements Action {
	
	@Override
    public void exec(Entity e) {
        e.do_pick();
    }
}

class Egg implements Action {
	
	@Override
    public void exec(Entity e) {
        e.do_egg();
    }
}

