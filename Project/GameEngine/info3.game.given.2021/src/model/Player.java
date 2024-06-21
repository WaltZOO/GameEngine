package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Character {
	boolean isPlayer1;
	boolean canRespawn;
	int col_sprite;
	public boolean isRunning;

	public Player(int x, int y, int speed, String direction, int reach, World dest, String filename,
			ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, boolean isPlayer1, boolean canRespawn, String fsm,
			World parent) throws Exception {

		super(x, y, speed, direction, reach, dest, filename, pickable, team, hp, damage, ennemies, allies, range, name,
				fsm, parent);

		sprites = loadSprite(filename, 10, 6);
		col_sprite = 0;
		isRunning = false;

		this.isPlayer1 = isPlayer1;
		this.canRespawn = canRespawn;
	}

	public Player(Player other) throws Exception {
		super(other.x, other.y, other.speed, other.direction, other.reach, other.dest, other.sprites,
				new ArrayList<String>(other.pickable), other.team, other.hp, other.damage,
				new ArrayList<String>(other.ennemies), new ArrayList<String>(other.allies), other.range, other.name,
				other.fsm, other.parent);

		this.isPlayer1 = other.isPlayer1;
		this.canRespawn = other.canRespawn;
	}

	
	
	@Override 
	public void do_die() {
		parent.qt.remove(this);
		if(canRespawn)
			parent.random_insert(this);
		else 
			parent = null;
		
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
	
	public void do_paint(Graphics g, int width, int height, Player p) {
		if(this.parent == null)
			return;
		
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

		// On dessine la hitbox
		if (this.isPlayer1) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		int sizex = (int) (hitbox * scale);
		g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

		// On dessine le sprite
		boolean isWest = false;
		switch (direction) {
		case "N":
			m_imageIndex = 2;
			if (isRunning) {
				m_imageIndex = 5;
			}
			break;
		case "S":
		case "SW":
		case "SE":
			m_imageIndex = 0;
			if (isRunning) {
				m_imageIndex = 3;
			}
			break;
		case "W":
		case "NW":
			isWest = true;
			m_imageIndex = 1;
			if (isRunning) {
				m_imageIndex = 4;
			}
			break;
		case "E":
		case "NE":
			m_imageIndex = 1;
			if (isRunning) {
				m_imageIndex = 4;
			}
			break;

		default:
			break;
		}
		col_sprite = (col_sprite + 1) % 6;
		sizex = (int) (sizex * 1.8);

		if (isWest) {
			g.drawImage(sprites[m_imageIndex * 6 + col_sprite], posxInWindow + offsetside + sizex / 2,
					posyInWindow - 4 * sizex / 6, -sizex, sizex, null);
		} else {
			g.drawImage(sprites[m_imageIndex * 6 + col_sprite], posxInWindow + offsetside - sizex / 2,
					posyInWindow - 4 * sizex / 6, sizex, sizex, null);
		}

		// dessin de la reach
		g.setColor(Color.BLUE);
		sizex = (int) (reach * scale);
		g.drawOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

	}


}
