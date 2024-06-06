package ai;

import model.Entity;

public interface Action {
    public void exec(Entity e);
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

class Turn implements Action {

    int dir;
    public Turn(int dir) {
        this.dir=dir;
    }
    @Override
    public void exec(Entity e) {
        e.do_turn(dir);
    }
}