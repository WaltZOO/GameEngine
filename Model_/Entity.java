public abstract class Entity {
    Automate aut;
    State current;
    // aut.step(this);

    public boolean eval_cell(Direction dir, Category cat) {
        return true;
    }

    State getState() {
        return this.current;
    }

    void setState(State s) {
        this.current = s;
    }

    Automate getAutomate() {
        return this.aut;
    }

    void setAutomate(Automate a) {
        this.aut = a;
    }

    public abstract void do_move();

    public abstract void do_pick();

    public abstract void do_egg();
}