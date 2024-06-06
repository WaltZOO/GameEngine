package model;

public class Vide extends Entity {
    public Vide(int x, int y,  Model m) {
        super(x,y,m);
    }
    public void do_move(int dir) {
        return;
	}

	@Override
	public void do_pick() {
		return;
	}

	@Override
	public void do_egg() {
		return;
	}

	@Override
	public void do_turn(int direction) {
        return;
	}
    public String toString() {
        return ".";
    }
}