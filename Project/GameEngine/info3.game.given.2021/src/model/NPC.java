package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

public class NPC extends Character {

	public NPC(int x, int y, int speed, String direction, int reach, int hitbox, World parent, World dest,
			String filename, ArrayList<Entity> pickable, String team, int hp, int damage, ArrayList<Entity> ennemies,
			ArrayList<Entity> allies, int range) throws IOException {
		super(x, y, speed, direction, reach, hitbox, parent, dest, filename, pickable, team, hp, damage, ennemies, allies,
				range);
		// TODO Auto-generated constructor stub
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
	public void do_store() {
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
	public void do_paint(Graphics g, int width, int height, int offsetside, int range) {
		// TODO Auto-generated method stub

	}

}
