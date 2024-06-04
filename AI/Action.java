public interface Action {
    public void exec(Entity e);
}

class Move implements Action {

    @Override
    void exec(Entity e) {
        e.do_move();
    }
}

class Pick implements Action {

    @Override
    void exec(Entity e) {
        e.do_pick();
    }
}

class Egg implements Action {

    @Override
    void exec(Entity e) {
        e.do_egg();
    }
}