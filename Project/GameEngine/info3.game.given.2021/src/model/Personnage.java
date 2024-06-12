package model;

import java.util.ArrayList;

public abstract class Personnage extends Entity{
	int vie;
	int damage;
	ArrayList<Entity> ennemies;
	ArrayList<Entity> allies;



	public Personnage(int x, int y, int vitesse, int reach, Model m, int vie, int damage) {
		super(x, y, vitesse, reach, m);
		this.vie=vie;
		this.damage=damage;
		this.ennemies = new ArrayList<Entity>();
		this.allies = new ArrayList<Entity>();
	}

	@Override
	public void do_move(int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void do_turn(int direction) {
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

}
