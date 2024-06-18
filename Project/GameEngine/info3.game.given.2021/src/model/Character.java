package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

public class Character extends Entity {
	String team;
	int hp;
	int damage;
	ArrayList<String> ennemies;
	ArrayList<String> allies;
	int range;

	public Character(int x, int y, int speed, String direction, int reach, World dest,
			String filename, ArrayList<String> pickable, String team, int hp, int damage,
			ArrayList<String> ennemies, ArrayList<String> allies, int range, String name, String fsm) throws IOException {
		
		super(x, y, speed, direction, reach, dest, filename, pickable, name, fsm);
		
		this.hp = hp;
		this.damage = damage;
		this.ennemies = ennemies;
		this.allies = allies;
		this.range = range;
		this.team = team;
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
