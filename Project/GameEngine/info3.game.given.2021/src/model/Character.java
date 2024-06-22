package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ai.Category;
import ai.Direction;
import ai.FSM;

public abstract class Character extends Entity {
	String team;
	int hp;
	int damage;
	ArrayList<String> ennemies;
	ArrayList<String> allies;
	int range;

	public Character(int x, int y, int speed, String direction, int reach, World dest, String filename,
			ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, String fsm, World parent) throws Exception {

		super(x, y, speed, direction, reach, dest, filename, pickable, name, fsm, parent);

		this.hp = hp;
		this.damage = damage;
		this.ennemies = ennemies;
		this.allies = allies;
		this.range = range;
		this.team = team;
	}

	public Character(int x, int y, int speed, String direction, int reach, World dest, BufferedImage[] filename,
			ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, FSM fsm, World parent) throws Exception {

		super(x, y, speed, direction, reach, dest, filename, pickable, name, fsm, parent);

		this.hp = hp;
		this.damage = damage;
		this.ennemies = ennemies;
		this.allies = allies;
		this.range = range;
		this.team = team;
	}

	public boolean eval(String dir, String cat, int radius) {
		ArrayList<Entity> listE = (ArrayList<Entity>) parent.qt.getEntitiesFromRadius(x, y, radius);
		ArrayList<Entity> listE_tri_cat = new ArrayList<Entity>();
		for (Entity e : listE) {
			if (cat == null || cat.equals(Category.ALL)) {
				listE_tri_cat = listE;
			} else {
				switch (cat) {
				case Category.P: // Pickable
					if (pickable.contains(e.name)) {
						listE_tri_cat.add(e);
					}
					break;
				case Category.O: // Obstacle
					if (e instanceof Bloc) {
						listE_tri_cat.add(e);
					}
					break;
				case Category.T: // Team
					if (allies.contains(e.name)) {
						listE_tri_cat.add(e);
					}
					break;
				case Category.A: // Autre
					if (ennemies.contains(e.name)) {
						listE_tri_cat.add(e);
					}
					break;

				default:
					break;
				}
			}
		}
		if (listE_tri_cat.isEmpty()) {
			if (cat.equals(Category.V))
				return true;
			return false;
		}
		if (dir == null) {

			return true;
		}
		if (dir.equals(Direction.F) || dir.equals(Direction.L) || dir.equals(Direction.R) || dir.equals(Direction.B))
			dir = relativeToAbsolue(dir);
		
		for (Entity e : listE_tri_cat) {
			if(isEntityInDirection(e, dir))
				return true;
		}
		return false;
	}

	// vraie si l’entité de la Catégorie demandée, la plus proche est dans la
	// Direction
	public boolean eval_closest(String dir, String cat) {
		return eval(dir, cat, range);
	}

	public boolean eval_cell(String dir, String cat) {
		return eval(dir, cat, reach);
	}

	@Override
	public void do_hit(String dir) {
		ArrayList<Entity> listE = (ArrayList<Entity>) parent.qt.getEntitiesFromRadius(x, y, reach);
		ArrayList<Entity> toHit = new ArrayList<Entity>();
		for (Entity e : listE) {
			if (ennemies.contains(e.name) && e instanceof Character && !e.equals(this))
				toHit.add(e);
		}

		if (toHit.isEmpty())
			return;
		if (dir == null) {
			dir = Direction.F;
		}

		if (dir.equals(Direction.F) || dir.equals(Direction.L) || dir.equals(Direction.R) || dir.equals(Direction.B))
			dir = relativeToAbsolue(dir);

		for (Entity e : toHit) {
			if (isEntityInDirection(e, dir)) {
				Character c = (Character) e;
				c.setHp(c.getHp() - this.damage);
			}
		}

	}

	void setHp(int hp) {
		System.out.print(hp);
		if (hp <= 0) {
			this.do_die();
		}
		this.hp = hp;
	}

	int getHp() {
		return this.hp;
	}

	@Override
	public void do_get() {
		// TODO Auto-generated method stub

	}

	@Override
	public abstract void do_egg(String direction, String category);
	

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
		g.setColor(Color.BLUE);
		g.fillOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

		// dessin de la reach
		g.setColor(Color.BLUE);
		sizex = (int) (reach * scale);
		g.drawOval(posxInWindow + offsetside - sizex / 2, posyInWindow - sizex / 2, sizex, sizex);

	}

}
