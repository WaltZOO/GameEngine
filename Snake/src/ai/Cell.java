package ai;

import model.Entity;


public class Cell implements Condition {
    int dir;
    int cat;

    public Cell(int f, int v) {
        this.dir = f;
        this.cat = v;
    }
    

    @Override
    public boolean eval(Entity e) {
        return e.eval_cell(dir, cat);
    }
 }