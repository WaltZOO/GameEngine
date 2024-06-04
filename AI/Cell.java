public class Cell implements Condition {
    Direction dir;
    Category cat;

    boolean eval(Entity e) {
        return e.eval_cell(dir, cat);
    }
}