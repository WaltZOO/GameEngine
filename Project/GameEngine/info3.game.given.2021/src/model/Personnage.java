package model;

import java.util.ArrayList;
import java.awt.Graphics;

public abstract class Personnage extends Entity{
	int vie;
	int damage;
	ArrayList<Entity> ennemies;
	ArrayList<Entity> allies;



	public Personnage(int x, int y, int vitesse, int reach, Monde m, int vie, int damage) {
		super(x, y, vitesse, reach, m);
		this.vie=vie;
		this.damage=damage;
		this.ennemies = new ArrayList<Entity>();
		this.allies = new ArrayList<Entity>();
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
	public void do_paint(Graphics g, int height, int width) {
		// TODO Auto-generated method stub
		
	}
}
