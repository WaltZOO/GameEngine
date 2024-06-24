package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import ai.Category;
import ai.Direction;

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
	
	public boolean eval(String dir, String cat, int radius) {
		ArrayList<Entity> listE = (ArrayList<Entity>) parent.qt.getEntitiesFromRadius(x, y, radius);
		ArrayList<Entity> listE_tri_cat = new ArrayList<Entity>();

		for (Entity e : listE) {
			if (cat == null) {
				listE_tri_cat = listE;
			} else {
				switch (cat) {
				case Category.P:
					if (pickable.contains(e.name)) {
						listE_tri_cat.add(e);
					}
					break;
				case Category.O:
					if (e instanceof Bloc) {
						listE_tri_cat.add(e);
					}
					break;
				case Category.ALL:
					listE_tri_cat.add(e);

					break;
				default:
					break;
				}
			}
		}
		if (listE_tri_cat.isEmpty())
			return false;
		if (dir == null) {

			return true;
		}
		if (dir.equals(Direction.F) || dir.equals(Direction.L) || dir.equals(Direction.R) || dir.equals(Direction.B))
			dir = relativeToAbsolue(dir);
		for (Entity e : listE_tri_cat) {
			switch (dir) {

			case Direction.N:
				if (e.y <= y && Math.abs(e.y - y) >= Math.abs(e.x - x)) {
					return true;
				}
				break;
			case Direction.S:
				if (e.y >= y && Math.abs(e.y - y) >= Math.abs(e.x - x)) {
					return true;
				}
				break;
			case Direction.E:
				if (e.x >= x && Math.abs(e.x - x) >= Math.abs(e.y - y)) {
					return true;
				}
				break;
			case Direction.W:
				if (e.x <= x && Math.abs(e.x - x) >= Math.abs(e.y - y)) {
					return true;
				}
				break;
			case Direction.NE:
				if (e.x >= x && e.y <= y) {
					return true;
				}
				break;
			case Direction.NW:
				if (e.x <= x && e.y <= y) {
					return true;
				}
				break;
			case Direction.SE:
				if (e.x >= x && e.y >= y) {
					return true;
				}
				break;
			case Direction.SW:
				if (e.x <= x && e.y >= y) {
					return true;
				}
				break;
			default:
				break;
			}
		}
		return false;
	}

	public boolean eval_cell(String dir, String cat) {
		return eval(dir, cat, reach);
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

	@Override
	public boolean eval_closest(String dir, String cat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void do_throw(String direction, String category) {
		// TODO Auto-generated method stub
		
	}



}
