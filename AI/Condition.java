public interface Condition {

    public boolean eval(Entity e);
    // return e.do_eval();

}

class Cell implements Condition {
    Direction dir;
    Category cat;

    @Override
    public boolean eval(Entity e) {
        return e.eval_cell(dir, cat);
    }
}