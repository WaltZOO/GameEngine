public abstract class Entity {
    Automate aut;
    State current;

    aut.step(this);

    public void eval_cell(Direction dir, Category cat) {
    }

public abstract void do_move();
public abstract void do_pick();
public abstract void do_egg();
}