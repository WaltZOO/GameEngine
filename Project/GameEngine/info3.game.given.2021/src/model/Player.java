package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import ai.Category;
import ai.Direction;

public class Player extends Character {
	boolean isPlayer1;
	boolean canRespawn;

	public Player(int x, int y, int speed, String direction, int reach, World dest, String filename,
			ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, boolean isPlayer1, boolean canRespawn, String fsm,
			World parent) throws Exception {

		super(x, y, speed, direction, reach, dest, filename, pickable, team, hp, damage, ennemies, allies, range, name,
				fsm, parent);
				
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
		if (canRespawn) {
			this.setHp(100);
			parent.random_insert(this);
		} else {
			parent = null;
		}

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

	public void do_throw(String direction, String category) {
		ArrayList<Entity> ListE = (ArrayList<Entity>) dest.qt.updateEntities();
		int dist = hitbox+1;
		
		if (direction == null)
			direction = Direction.F;
		if (category == null)
			category = Category.ALL;
		direction = relativeToAbsolue(direction);

		Player temp;
		
		try {
			temp = new Player(this);
		} catch (Exception e) {
			temp = null;
			e.printStackTrace();
		}

		switch (direction) {
		case Direction.N:
			temp.y -= dist;
			break;

		case Direction.S:
			temp.y += dist;
			break;

		case Direction.W:
			temp.x -= dist;
			break;

		case Direction.E:
			temp.x += dist;
			break;
		default:
			break;
		}
		
		for(Entity e : ListE) {
			if(pickable.contains(e.name)) {
				if (temp.eval(direction, Category.V, hitbox)) {
					e.parent.qt.remove(e);
					e.x = temp.x;
					e.y = temp.y;
					parent.qt.insert(e);
					e.parent = this.parent;
					return;
				}
			}
		}
	}

	public void do_paint(Graphics g, int width, int height, Player p) {
		if (this.parent == null)
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
		int sizex = (int) (hitbox * scale);
		/*
		// On dessine la hitbox
		if (this.isPlayer1) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		
		g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);
		*/

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
		
		sizex = (int) (sizex * 1.8);
		
		if (isWest) {
			g.drawImage(sprites[m_imageIndex * 6 + col_sprite], posxInWindow + offsetside + sizex / 2,
					posyInWindow - 4 * sizex / 6, -sizex, sizex, null);
		} else {
			g.drawImage(sprites[m_imageIndex * 6 + col_sprite], posxInWindow + offsetside - sizex / 2,
					posyInWindow - 4 * sizex / 6, sizex, sizex, null);
		}
		
		// on change le sprite
		if (elasped > 80)
			col_sprite = (col_sprite+1)%6;

		// dessin de la reach
		Color c = new Color(100,100,100,40);
		g.setColor(c);
		sizex = (int) (reach * scale);
		g.drawOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

	}

}
