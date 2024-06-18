package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

public class Character extends Entity {
	String team;
	int hp;
	int damage;
	ArrayList<Entity> ennemies;
	ArrayList<Entity> allies;
	int range;

	public Character(int x, int y, int speed, String direction, int reach, int hitbox, World parent, World dest,

			String filename, ArrayList<String> pickable, String team, int hp, int damage,
			ArrayList<String> ennemies, ArrayList<String> allies, int range, String name) throws IOException {
		
		super(x, y, speed, direction, reach, hitbox, parent, dest, filename, pickable, name);
		this.hp = hp;
		this.damage = damage;
		this.ennemies = new ArrayList<Entity>();
		this.allies = new ArrayList<Entity>();
		this.range = range;
	}

	@Override
	public boolean eval_cell(String dir, String cat) {
		// TODO Auto-generated method stub
		return false;
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
		int sizex = (int) (hitbox * scale);

		int posxInWindow = (int) ((x - p.x) * scale + width / 2);
		int posyInWindow = (int) ((y - p.y) * scale + height / 2);

		g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

		// dessin de la reach
		g.setColor(Color.BLUE);
		sizex = (int) (hitbox * scale);
		g.drawOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);
	}

}
