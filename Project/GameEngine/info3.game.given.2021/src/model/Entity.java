package model;

import java.util.ArrayList;

import ai.Direction;
import ai.FSM;
import ai.State;

public abstract class Entity {
	int x, y; // Position
	int direction;
	int vitesse; // Vitesse pour le Move()
	int hitbox; // Rayon de collisions
	int reach;
	
	ArrayList<Entity> pickable;
	
	// Sprite to add
	FSM fsm;
	State state;
	Model model;

	public Entity(int x, int y, int vitesse, int reach, Model m) {
		this.x = x;
		this.y = y;
		this.direction = Direction.W;
		this.vitesse = vitesse;
		this.hitbox=1;
		this.reach = reach;
		
		this.pickable = new ArrayList<Entity>();

		this.fsm = new FSM();
		this.state = new State(1);
		this.model = m;
	}
	
	public int getDir() {
		return this.direction;
	}
	
	public void setDir(int dir) {
		this.direction=dir;
	}
	
	
	//public boolean eval_cell(int dir, int cat);
	
	public abstract void do_move(int direction);

	public abstract void do_turn(int direction);

	public abstract void do_pick();

	public abstract void do_egg();

}
