package ai;

import model.Entity;

public class Cell implements Condition {
    String dir;
    String cat;

    public Cell(String f, String v) {
        this.dir = f;
        this.cat = v;
    }

    public Cell(String f, boolean isDir) {
        if (isDir) {
            this.dir = f;
        } else {
            this.cat = f;
        }
    }

    public Cell() {
    }

    @Override
    public boolean eval(Entity e) {
        return e.eval_cell(dir, cat);
    }
}