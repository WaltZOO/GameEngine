package model;

public class Apple extends Entity {
	
	Model model;
	
	public Apple(int x, int y, Model m) {
		super(x, y, m);
		this.model = m;
		model.getEntity(x, y).replace(this);
	}

	@Override
	public void do_move(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_egg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_turn(int direction) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		return "@";
	}

}
