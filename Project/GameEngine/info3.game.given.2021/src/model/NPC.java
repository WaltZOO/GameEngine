package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import ai.Category;
import ai.Direction;

public class NPC extends Character {

	Model m;

	public NPC(int x, int y, int speed, String direction, int reach, World dest, String filename,
			ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, String fsm, World parent) throws Exception {
		super(x, y, speed, direction, reach, dest, filename, pickable, team, hp, damage, ennemies, allies, range, name,
				fsm, parent);
	}

	public NPC(NPC other) throws Exception {
		super(other.x, other.y, other.speed, other.direction, other.reach, other.dest, other.sprites,
				new ArrayList<>(other.pickable), other.team, other.hp, other.damage, new ArrayList<>(other.ennemies),
				new ArrayList<>(other.allies), other.range, other.name, other.fsm, other.parent);
	}

	public void setModel(Model m) {
		this.m = m;
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
		if (parent.Entity_Cap < parent.qt.nbEntity)
			return;

		int dist = hitbox + 1;
		if (direction == null)
			direction = Direction.F;
		direction = relativeToAbsolue(direction);
		if (direction == null)
			direction = Direction.F;
		direction = relativeToAbsolue(direction);
		Entity temp;
		try {
			temp = getEntitybyCat(category);
			if (temp instanceof NPC) {
				temp = new NPC((NPC) temp);
				temp.parent = parent;
				((NPC) temp).setModel(m);
			} else if (temp instanceof Bloc) {
				temp = new Bloc((Bloc) temp);
				temp.parent = parent;
			} else {
				temp = null;
			}
		} catch (Exception e) {
			temp = null;
			e.printStackTrace();
		}
		temp.x=x;
		temp.y=y;
		switch (direction) {
		case Direction.N:
			temp.y -= dist;
			break;

		case Direction.S:

			temp.y += dist;
			break;

		case Direction.W:

			temp.x += dist;
			break;

		case Direction.E:

			temp.x -= dist;
			break;
		default:
			break;
		}
		if (temp.eval(direction, Category.V, hitbox)) {
			parent.qt.insert(temp);
		}
	}

	private Entity getEntitybyCat(String category) {
		if (category == null || category.equals(Category.M)) {
			return this;
		}
		for (World world : m.worlds) {
			List<Entity> listE = world.qt.updateEntities();
			if (category.equals(Category.ALL)) {
				int index = (int) Math.random() * listE.size();
				return listE.get(index);
			}
			for (Entity e : listE) {

				switch (category) {
				case Category.A:
					if (!allies.contains(e.name)) {
						return e;
					}
					break;
				case Category.P:
				case Category.T:
					if (allies.contains(e.name) && e != this) {
						return e;
					}
					break;
				case Category.ALL:
					return e;

				}
			}
		}
		return this;
	}

	@Override
	public void do_wait() {

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
		/*
		 * // On dessine la hitbox if (this.isPlayer1) { g.setColor(Color.GREEN); } else
		 * { g.setColor(Color.RED); }
		 * 
		 * g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2,
		 * sizex, sizex);
		 */

		// On dessine le sprite
		boolean isWest = false;
		switch (direction) {
		case "N":
			m_imageIndex = 2;
			if (isRunning) {
				m_imageIndex = 5;
			}
			if (ishitting) {
				m_imageIndex = 8;
				col_sprite = col_sprite % 4;
			}
			break;
		case "S":
		case "SW":
		case "SE":
			m_imageIndex = 0;
			if (isRunning) {
				m_imageIndex = 3;
			}
			if (ishitting) {
				m_imageIndex = 6;
				col_sprite = col_sprite % 4;
			}
			break;
		case "W":
		case "NW":
			isWest = true;
			m_imageIndex = 1;
			if (isRunning) {
				m_imageIndex = 4;
			}
			if (ishitting) {
				m_imageIndex = 7;
				col_sprite = col_sprite % 4;
			}
			break;
		case "E":
		case "NE":
			m_imageIndex = 1;
			if (isRunning) {
				m_imageIndex = 4;
			}
			if (ishitting) {
				m_imageIndex = 7;
				col_sprite = col_sprite % 4;
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
		if (elasped > 40)
			col_sprite = (col_sprite + 1) % 6;
		if (col_sprite > 3) {
			ishitting = false;
		}
	}

	@Override
	public void do_throw(String direction, String category) {
		// TODO Auto-generated method stub

	}
}
