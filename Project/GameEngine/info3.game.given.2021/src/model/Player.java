package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Character {
	boolean isPlayer1;
	boolean canRespawn;

	public Player(int x, int y, int speed, String direction, int reach, int hitbox, World parent, World dest,
			String filename, ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, boolean isPlayer1, boolean canRespawn)
			throws IOException {

		super(x, y, speed, direction, reach, hitbox, parent, dest, filename, pickable, team, hp, damage, ennemies,
				allies, range, name);

		this.isPlayer1 = isPlayer1;
		this.canRespawn = canRespawn;
	}

	public void do_paint(Graphics g, int width, int height, int offsetside, int range) {
		// BufferedImage img = sprites[m_imageIndex];
		// g.drawImage(img, x, y, scale * img.getWidth(), scale * img.getHeight(),
		// null);
		g.setColor(Color.RED);
		if (isPlayer1) {
			g.setColor(Color.BLUE);
		}
		int size = hitbox * hitbox * hitbox / range;
		g.fillOval(width / 2 + offsetside - size / 2, height / 2 - size / 2, size, size);

		// dessin de la reach
		g.setColor(Color.BLUE);
		size =  reach * reach * reach / range ;
		g.drawOval(width / 2 + offsetside - size / 2, height / 2 - size / 2, size, size);
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
