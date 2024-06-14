package model;

import java.util.ArrayList;
import java.awt.Graphics;

public abstract class Personnage extends Entity {
	int vie;
	int damage;
	ArrayList<Entity> ennemies;
	ArrayList<Entity> allies;
	int range;

	public Personnage(int x, int y, int vitesse, int reach, int range, int hitbox, Monde m, int vie, int damage) {
		super(x, y, vitesse, reach, hitbox, m);
		this.vie = vie;
		this.damage = damage;
		this.ennemies = new ArrayList<Entity>();
		this.allies = new ArrayList<Entity>();
		this.range = range;
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

	@Override
	public void do_paint(Graphics g, int width, int height, int offsetside,int range) {
		// TODO Auto-generated method stub

	}
}
