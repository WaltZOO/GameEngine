package model;

public abstract class Bloc extends Entity{
	

	public Bloc(int x, int y, int vitesse, int reach, Monde m) {
		super(x, y, vitesse, reach, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void do_move(String direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void do_turn(String direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void do_pick(String direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void do_egg(String direction, String categoryq) {
		// TODO Auto-generated method stub
		
	}

}
