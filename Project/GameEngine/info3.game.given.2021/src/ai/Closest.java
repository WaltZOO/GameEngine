package ai;

import model.Entity;

public class Closest implements Condition {
    String dir;
    String cat;

    public Closest(String f, String v) {
        this.dir = f;
        this.cat = v;
    }

    public Closest(String f, boolean isDir) {
        if (isDir) {
            this.dir = f;
        } else {
            this.cat = f;
        }
    }

    public Closest() {
    }

    @Override
    public boolean eval(Entity e) {
        return e.eval_closest(dir, cat);
    }
}
