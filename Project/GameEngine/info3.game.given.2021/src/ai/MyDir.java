package ai;

import model.Entity;

public class MyDir implements Condition {

    String direction;

    MyDir() {
    }

    MyDir(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean eval(Entity e) {
        return e.eval_mydir(direction);
    }
}
