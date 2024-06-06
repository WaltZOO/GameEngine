package model;

import java.util.ArrayList;

import ai.*;

public class Snake extends Entity {
	SnakeHead head;
	ArrayList<SnakeBody> bodies;

	public Snake(SnakeHead head, int x, int y, Model model) {
		super(x, y, model);
		this.head = head;
		this.bodies = new ArrayList<SnakeBody>();

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

	// public Snake(SnakeHead head, ArrayList<SnakeBody> bodies) {
	// this.head = head;
	// this.bodies = bodies;
	// super.fsm = new FSM();
	// //fsm.add_transition(new Transition())
	// }

	@Override
	public void do_move(int dir) {
		bodies.add(new SnakeBody(head.getX(), head.getY(), model, bodies.size()));
		model.set(head.getX(), head.getY(), bodies.get(bodies.size() - 1));
		head.do_move(dir);
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

	public SnakeHead(int x, int y, Model m) {
		super(x, y, m);

	}

	@Override
	public void do_move(int dir) {
		this.do_turn(dir);
		switch (super.direction) {
			case Direction.N:
				y--;
				break;
			case Direction.E:
				x++;
				break;
			case Direction.S:
				y++;
				break;
			case Direction.W:
				x--;
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
	int duration;

	public SnakeBody(int x, int y, Model m, int duration) {
		super(x, y, m);
		this.duration = duration;

	}

	@Override
	public void do_move(int direction) {
		duration--;
		if (duration == 0) {
			model.set(x, y, new Vide(x, y, model));
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