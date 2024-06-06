package model;

import java.util.ArrayList;

import ai.*;

public class Snake extends Entity {
	SnakeHead head;
	ArrayList<SnakeBody> bodies;

	public Snake(int x, int y, Model model) {
		super(x, y, model);
		this.bodies = new ArrayList<SnakeBody>();
		this.head = new SnakeHead(0, 0, model,this);
		model.set(x, y, head);
		SnakeBody body = new SnakeBody(x+1, y, model, 1,this);
		bodies.add(body);
	}

	// public Snake(SnakeHead head, ArrayList<SnakeBody> bodies) {
	// this.head = head;
	// this.bodies = bodies;
	// super.fsm = new FSM();
	// //fsm.add_transition(new Transition())
	// }

	@Override
	public void do_move(int dir) {
		bodies.add(new SnakeBody(head.getX(), head.getY(), model, bodies.size(),this));
		model.set(head.getX(), head.getY(), bodies.get(bodies.size() - 1));
		//head.do_move(dir);
		for (int i = 0; i < bodies.size(); i++)
			bodies.get(i).do_move(dir);
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
	public void do_turn(int dir) {
		head.do_turn(dir);
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}
}

class SnakeHead extends Entity {
	Snake snk;

	
	public SnakeHead(int x, int y, Model m, Snake snk) {
		super(x, y, m);
		this.snk = snk;
		// Si libre devant, avancer
		State src = new State(1);
		ArrayList<Action> move = new ArrayList<Action>();
		move.add(new Move(Direction.F));
		Condition cond = new Cell(Direction.F, Category.V); // Si libre devant
		fsm.add_transition(new Transition(src, src, cond, move));

		// Si libre droite, tourner droite
		cond = new Cell(Direction.R, Category.V);
		move = new ArrayList<Action>();
		move.add(new Move(Direction.R));
		fsm.add_transition(new Transition(src, src, cond, move));

		// Si libre gauche, tourner gauche
		cond = new Cell(Direction.L, Category.V);
		move = new ArrayList<Action>();
		move.add(new Move(Direction.L));
		fsm.add_transition(new Transition(src, src, cond, move));
	}

	@Override
	public void do_move(int dir) {
		snk.do_move(dir);
		this.do_turn(dir);
		
		switch (super.direction) {
			case Direction.N:
				y = (y-1)%model.getSize();
				break;
			case Direction.E:
				x = (x+1)%model.getSize();
				break;
			case Direction.S:
				y = (y+1)%model.getSize();
				break;
			case Direction.W:
				x = (x-1)%model.getSize();
				break;
			default:
				break;
		}
		model.set(x, y, this);

	}
	int getX(){
		return x;
	}
	int getY(){
		return y;
	}

	public void do_pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_egg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_turn(int dir) {
		switch (dir) {

			// absolute
			case Direction.E:
				super.direction = Direction.E;
				break;
			case Direction.W:
				super.direction = Direction.W;
				break;
			case Direction.N:
				super.direction = Direction.N;
				break;
			case Direction.S:
				super.direction = Direction.S;
				break;

			
			case Direction.R:
				switch (super.direction) {
					case Direction.E:
						super.direction = Direction.S;
						break;
					case Direction.W:
						super.direction = Direction.N;
						break;
					case Direction.N:
						super.direction = Direction.E;
						break;
					case Direction.S:
						super.direction = Direction.W;
						break;
				}
				break;
			case Direction.L:
				switch (super.direction) {
					case Direction.E:
						super.direction = Direction.N;
						break;
					case Direction.W:
						super.direction = Direction.S;
						break;
					case Direction.N:
						super.direction = Direction.W;
						break;
					case Direction.S:
						super.direction = Direction.E;
						break;
				}
				break;

			default:
				break;
		}
	}

	public String toString() {
		switch (direction) {
			case Direction.E:
				return "►";
			case Direction.W:
				return "◄";
			case Direction.N:
				return "▲";
			case Direction.S:
				return "▼";
			default:
				return " ";
		}
	}
}

class SnakeBody extends Entity {
	Snake snk;
	int duration;

	public SnakeBody(int x, int y, Model m, int duration, Snake snk) {
		super(x, y, m);
		this.duration = duration;
		this.snk = snk;
	}

	@Override
	public void do_move(int direction) {
		duration--;
		if (duration == 0) {
			model.set(x, y, new Vide(x, y, model));
			snk.bodies.remove(this);
		}
	}

	@Override
	public void do_pick() {

	}

	@Override
	public void do_egg() {
	}

	@Override
	public void do_turn(int direction) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return String.valueOf(duration);
	}
}