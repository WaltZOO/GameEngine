package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bloc extends Entity {

	public Bloc(int x, int y, int speed, String direction, int reach, World world_dest, String sprite,
			ArrayList<String> pickable, String filename, String fsm, World world) throws Exception {
		super(x, y, speed, direction, reach, world_dest, sprite, pickable, filename, fsm, world);
	}

	public Bloc(Bloc other) throws Exception {
		// Appel du constructeur parent avec les propriétés de l'autre objet Bloc
		super(other.x, other.y, other.speed, other.direction, other.reach, other.dest, other.sprites,
				new ArrayList<String>(other.pickable), other.name, other.fsm, other.parent);
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
	public void do_paint(Graphics g, int width, int height, Player p) {
		// offsetside
		int offsetside = 0;
		if (p != null) {
			if (p.isPlayer1) {
				offsetside = -width / 4;
			} else {
				offsetside = width / 4;
			}
		}

		// dessin de la hitbox
		g.setColor(Color.blue);

		float scale = (float) width / (2 * p.range);

		int posxInWindow = (int) ((x - p.x) * scale + width / 2);
		int posyInWindow = (int) ((y - p.y) * scale + height / 2);
		
		// on fait un produit en croix pour la taille
		int sizex = (int) (((float )hitbox) * scale);
		g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

	}

}
