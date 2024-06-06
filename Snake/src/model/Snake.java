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
		State src = new State("src");
		Action move = new Move(Direction.F);
		Condition cond = new Cell(Direction.F, Category.V); // Si libre devant
		fsm.add_transition(new Transition(src, src, move, cond));

		// Si libre droite, tourner droite
		Condition cond = new Cell(Direction.R, Category.V);
		Action move = new Action("move");

		fsm.add_transition(new Transition(src, src, move, cond));

		// Si libre gauche, tourner gauche
		Condition cond = new Cell(Direction.L, Category.V);
		Action move = new Action("move");
		fsm.add_transition(new Transition(src, src, move, cond));
	}

	public Snake(SnakeHead head, ArrayList<SnakeBody> bodies) {
        this.head = head;
        this.bodies = bodies;
        super.fsm = new FSM();
        fsm.add_transition(new Transition())
    }

	@Override
	public void do_move(int dir) {
		switch (dir) {

			// absolute
			case Direction.E:
				x++;
				break;
			case Direction.W:
				x--;
				break;
			case Direction.N:
				y++;
				break;
			case Direction.S:
				y--;
				break;

			// relative
			case Direction.F:
				switch (head.direction) {
					case Direction.E:
						x++;
						break;
					case Direction.W:
						x--;
						break;
					case Direction.N:
						y++;
						break;
					case Direction.S:
						y--;
						break;
				}
				break;
			case Direction.R:
				switch (head.direction) {
					case Direction.E:
						y++;
						break;
					case Direction.W:
						y--;
						break;
					case Direction.N:
						x--;
						break;
					case Direction.S:
						x++;
						break;
				}
				break;
			case Direction.L:
				switch (head.direction) {
					case Direction.E:
						y--;
						break;
					case Direction.W:
						y++;
						break;
					case Direction.N:
						x++;
						break;
					case Direction.S:
						x--;
						break;
				}
				break;

			default:
				break;
		}
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
	public void do_turn(int direction) {
		this.do_move(direction);
	}

}

class SnakeHead extends Entity {

	public SnakeHead(int x, int y, Model m) {
		super(x, y, m);
		
	}

	@Override
	public void do_move(int direction) {
		// TODO Auto-generated method stub

	}

	public void do_pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_egg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_turn(int direction) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'do_turn'");
	}

	@Override
	public String toString() {
		return "@";
	}

}

class SnakeBody extends Entity {
	int duration;

	public SnakeBody(int x, int y, Model m) {
		super(x, y, m);
		
	}


	@Override
	public void do_move(int direction) {
		duration--;
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