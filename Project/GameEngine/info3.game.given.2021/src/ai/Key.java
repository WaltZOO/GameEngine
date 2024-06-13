package ai;

import model.Entity;

public class Key implements Condition {
    String key;

    public Key(String key) {
        this.key = key;
    }

    public Key() {
    }

    @Override
    public boolean eval(Entity e) {
        return e.eval_key(key);
    }
}
