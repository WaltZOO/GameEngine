package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Character {
	boolean isPlayer1;
	boolean canRespawn;

	public Player(int x, int y, int speed, String direction, int reach, World dest,
			String filename, ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, boolean isPlayer1, boolean canRespawn, String fsm, World parent)
			throws IOException {

		super(x, y, speed, direction, reach, dest, filename, pickable, team, hp, damage, ennemies,
				allies, range, name, fsm, parent);

		this.isPlayer1 = isPlayer1;
		this.canRespawn = canRespawn;
	}

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
		g.setColor(Color.RED);
		if (isPlayer1) {
			g.setColor(Color.GREEN);
		}
		if(this == p) {
			float scale = (width / 2) / (float) p.range;

			int sizex = (int) ((float) hitbox * scale);
			g.fillOval(width / 2 + offsetside - sizex / 2, height / 2 - sizex / 2, sizex, sizex);

			// dessin de la reach
			g.setColor(Color.BLUE);
			sizex = (int) (reach * scale);
			g.drawOval(width / 2 + offsetside - sizex / 2, height / 2 - sizex / 2, sizex, sizex);
		
		}else {
			
			// dessin de la hitbox
			float scale = (float) width / (2 * p.range);
			int sizex = (int) (hitbox * scale);

			int posxInWindow = (int) ((x - p.x) * scale + width / 2);
			int posyInWindow = (int) ((y - p.y) * scale + height / 2);

			g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

			// dessin de la reach
			g.setColor(Color.BLUE);
			sizex = (int) (reach * scale);
			g.drawOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);
		}
		

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
	public boolean eval_cell(String dir, String cat) {
		// TODO Auto-generated method stub
		return false;
	}
}
