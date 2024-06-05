package model;

import ai.*;

public class Snake extends Entity {
	int longueur;
	boolean head;

	public Snake(int longueur, boolean head) {
        this.longueur = longueur;
        this.head = head;
        this.aut = new Automate();
        aut.add_transition(new Transition())
    }

	@Override
	public void do_move() {
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
