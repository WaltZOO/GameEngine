package model;

import java.awt.Graphics;
import java.util.ArrayList;

public class Bloc extends Entity {

	public Bloc(int x, int y, int speed, String direction, int reach, int hitbox, World parent, World dest,
			String filename, ArrayList<String> pickable, String name) throws Exception {
		super(x, y, speed, direction, reach, hitbox, parent, dest, filename, pickable, name);
	}

	@Override
	public boolean eval_cell(String dir, String cat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void do_move(String direction2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_pick(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_hit(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_egg(String direction, String category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_turn(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_wait() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_paint(Graphics g, int width, int height, Player p) {
		// TODO Auto-generated method stub

	}

}
