package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bloc extends Entity {

	public Bloc(int x, int y, int speed, String direction, int reach, World world_dest, String sprite,
			ArrayList<String> pickable, String filename, String fsm, World world) throws Exception {
		super(x, y, speed, direction, reach, world_dest, sprite, pickable, filename, fsm, world);

		sprites = loadSprite(sprite, 1, 4);
		m_imageIndex = 0;

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
		// scale
		float scale = (float) height / p.range;

		// offsetside
		int offsetside = 0;
		if (p != null) {
			if (p.isPlayer1) {
				offsetside = -width / 4;
			} else {
				offsetside = width / 4;
			}
		}

		int posxInWindow = (int) ((x - p.x) * scale + width / 2);
		int posyInWindow = (int) ((y - p.y) * scale + height / 2);

		int sizex = (int) (hitbox * scale);
		g.setColor(Color.blue);
		g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

		// On dessine le sprite
		sizex = (int) (sizex * 2);

		g.drawImage(sprites[m_imageIndex], posxInWindow + offsetside - 4 * sizex / 6 , posyInWindow - 4 * sizex / 6, sizex,
				sizex, null);
		m_imageIndex = (m_imageIndex +1)%4;

	}

}
