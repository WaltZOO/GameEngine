package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

import ai.Category;
import ai.Direction;
import ai.FSM;
import ai.FSMGenerator;
import ai.State;
import gal.ast.AST;
import gal.parser.Parser;

public abstract class Entity {
	final static float CDD = 0.71f;

	// Mondes
	World parent;
	World dest; // Monde destination pour le pick
	ArrayList<String> pickable; // Liste des entité prenable

	boolean checked;

	// Mouvement
	int x, y; // Position
	int speed; // Vitesse pour le Move()
	String direction;
	int reach; // Rayon de frappe
	int hitbox;

	// Graphique
	BufferedImage[] sprites; // Sprites
	int m_imageIndex; // Index de l'image à afficher

	// Automate
	FSM fsm; // Automate de l'entité
	State state; // Etat de départ

	String name;

	public Entity(int x, int y, int speed, String direction, int reach, World world_dest, String sprite,
			ArrayList<String> pickable2, String name, String fsm2, World world) throws Exception {

		// Monde
		this.parent = world;
		this.dest = world_dest;
		this.pickable = pickable2;
		this.name = name;
		// Mouvement
		this.x = x;
		this.y = y;
		this.hitbox = parent.hitbox;

		if (direction == null)
			this.direction = Direction.E;
		else
			this.direction = direction;

		this.speed = speed;
		this.reach = reach;

		// Graphique
		this.sprites = loadSprite(sprite, 4, 5);
		this.m_imageIndex = 0;

		// Automate
		AST ast = Parser.from_file(fsm2);
		FSMGenerator fsmg = new FSMGenerator();
		ast.accept(fsmg);
		fsm = fsmg.getOutput().get(0);
		state = fsm.getStart();

		// Key
		this.keys = new ArrayList<String>();
	}

	public Entity(int x, int y, int speed, String direction, int reach, World world_dest, BufferedImage[] sprite,
			ArrayList<String> pickable2, String name, FSM fsm2, World world) throws Exception {

		// Monde
		this.parent = world;
		this.dest = world_dest;
		this.pickable = pickable2;
		this.name = name;
		this.hitbox = parent.hitbox;
		// Mouvement
		this.x = x;
		this.y = y;

		if (direction == null)
			this.direction = Direction.E;
		else
			this.direction = direction;

		this.speed = speed;
		this.reach = reach;

		// Graphique
		this.sprites = sprite;
		this.m_imageIndex = 0;

		// Automate

		fsm = fsm2;
		state = fsm.getStart();

		// Key
		this.keys = new ArrayList<String>();
	}

	public Entity(int x, int y, int speed, String direction, int reach, World dest, String filename,
			ArrayList<String> pickable, String name, FSM fsm, World parent, int hitbox) throws Exception {

		// Monde
		this.parent = parent;
		this.dest = dest;
		this.pickable = pickable;
		this.name = name;
		this.hitbox = parent.hitbox;

		state = new State("Init");
		AST ast = Parser.from_file("./resources/test.gal");
		FSMGenerator fsmg = new FSMGenerator();
		ast.accept(fsmg);
		fsm = fsmg.getOutput().get(0);
		// Mouvement
		this.x = x;
		this.y = y;

		if (direction == null)
			this.direction = Direction.E;
		else
			this.direction = direction;

		this.speed = speed;
		this.reach = reach;

		// Graphique
		this.sprites = loadSprite(filename, 4, 5);
		this.m_imageIndex = 0;

		// Automate
		// this.fsm = fsm;
		// this.fsm = new FSM();
		// this.state = new State(1);

		this.name = name;
		this.hitbox = hitbox;

		// Key
		this.keys = new ArrayList<String>();
	}

	public String getDir() {
		return this.direction;
	}

	public void setDir(String dir) {
		this.direction = dir;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	// key

	public ArrayList<String> keys;

	private double elasped;

	public boolean eval_key(String key) {
		/*
		 * System.out.println(key); System.out.println(keys.toString());
		 * System.out.println(keys.contains(key));
		 */
		return keys.contains(key);
	}

	public abstract boolean eval_cell(String dir, String cat);

	public abstract boolean eval_closest(String dir, String cat);

	public boolean isEntityInDirection(Entity e, String dir) {
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
		return false;
	}

	// vraie si l'entité est orientée dans la Direction
	public boolean eval_mydir(String direction) {
		return (this.direction.equals(direction));
	}

	public String relativeToAbsolue(String d) {

		switch (direction) {
		case Direction.N:
			switch (d) {
			case Direction.R:
				return Direction.E;
			case Direction.L:
				return Direction.W;
			case Direction.B:
				return Direction.S;
			default:
				return Direction.N;
			}
		case Direction.S:
			switch (d) {
			case Direction.R:
				return Direction.W;
			case Direction.L:
				return Direction.E;
			case Direction.B:
				return Direction.N;
			default:
				return Direction.S;
			}
		case Direction.E:
			switch (d) {
			case Direction.R:
				return Direction.S;
			case Direction.L:
				return Direction.N;
			case Direction.B:
				return Direction.W;
			default:
				return Direction.E;
			}
		case Direction.W:
			switch (d) {
			case Direction.R:
				return Direction.N;
			case Direction.L:
				return Direction.S;
			case Direction.B:
				return Direction.E;
			default:
				return Direction.W;
			}
		case Direction.NW:
			switch (d) {
			case Direction.R:
				return Direction.NE;
			case Direction.L:
				return Direction.SW;
			case Direction.B:
				return Direction.SE;
			default:
				return Direction.NW;
			}
		case Direction.NE:
			switch (d) {
			case Direction.R:
				return Direction.SE;
			case Direction.L:
				return Direction.NW;
			case Direction.B:
				return Direction.SW;
			default:
				return Direction.NE;
			}
		case Direction.SW:
			switch (d) {
			case Direction.R:
				return Direction.NW;
			case Direction.L:
				return Direction.SE;
			case Direction.B:
				return Direction.NE;
			default:
				return Direction.SW;
			}
		case Direction.SE:
			switch (d) {
			case Direction.R:
				return Direction.SW;
			case Direction.L:
				return Direction.NE;
			case Direction.B:
				return Direction.NW;
			default:
				return Direction.SE;
			}
		}
		return Direction.E;
	}

	public void moveF() {
		int dx = x;
		int dy = y;

		if (this instanceof Player) {
			((Player) this).isRunning = true;
		}

		double disttemp = (double) this.speed / 3.0f;

		switch (this.direction) {
		case Direction.S:
			dy += (int) Math.ceil(disttemp);
			break;
		case Direction.N:
			dy -= (int) Math.ceil(disttemp);
			break;
		case Direction.E:
			dx += (int) Math.ceil(disttemp);
			break;
		case Direction.W:
			dx -= (int) Math.ceil(disttemp);
			break;
		case Direction.SE:
			dy += (int) Math.ceil(disttemp * CDD);
			dx += (int) Math.ceil(disttemp * CDD);
			break;
		case Direction.SW:
			dy += (int) Math.ceil(disttemp * CDD);
			dx -= (int) Math.ceil(disttemp * CDD);
			break;
		case Direction.NE:
			dy -= (int) Math.ceil(disttemp * CDD);
			dx += (int) Math.ceil(disttemp * CDD);
			break;
		case Direction.NW:
			dy -= (int) Math.ceil(disttemp * CDD);
			dx -= (int) Math.ceil(disttemp * CDD);
			break;
		default:
			break;
		}
		// on regarde les collisions avec les voisins
		ArrayList<Entity> listE = (ArrayList<Entity>) parent.qt.getEntitiesFromRadius(dx, dy, hitbox);
		if (listE.size() <= 1 && dx < parent.size && dy < parent.size && dx >= 0 && dy >= 0
				&& (listE.size() == 0 || listE.get(0) == this)) {
			this.parent.qt.remove(this);
			x = dx;
			y = dy;
			this.parent.qt.insert(this);
		}
	}

	public void do_move(String direction) {

		if (direction == null) {
			direction = Direction.F;
		}

		do_turn(direction);
		moveF();
	}

	public void do_turn(String direction) {
		if (direction == null) {
			direction = Direction.L;
		}
		switch (direction) {
		case Direction.F:
			break;

		case Direction.B:
			switch (this.direction) {
			case Direction.S:
				this.direction = Direction.N;
				break;
			case Direction.N:
				this.direction = Direction.S;
				break;
			case Direction.E:
				this.direction = Direction.W;
				break;
			case Direction.W:
				this.direction = Direction.E;

				break;
			case Direction.SE:
				this.direction = Direction.NW;
				break;
			case Direction.SW:
				this.direction = Direction.NE;
				break;
			case Direction.NE:
				this.direction = Direction.SW;
				break;
			case Direction.NW:
				this.direction = Direction.SE;
				break;
			default:
				break;
			}
			break;

		case Direction.L:
			switch (this.direction) {
			case Direction.S:
				this.direction = Direction.E;
				break;
			case Direction.N:
				this.direction = Direction.W;
				break;
			case Direction.E:
				this.direction = Direction.N;
				break;
			case Direction.W:
				this.direction = Direction.S;
				break;
			case Direction.SE:
				this.direction = Direction.NE;
				break;
			case Direction.SW:
				this.direction = Direction.SE;
				break;
			case Direction.NE:
				this.direction = Direction.NW;
				break;
			case Direction.NW:
				this.direction = Direction.SW;
				break;
			default:
				break;
			}
			break;

		case Direction.R:
			switch (this.direction) {
			case Direction.S:
				this.direction = Direction.W;
				break;
			case Direction.N:
				this.direction = Direction.E;
				break;
			case Direction.E:
				this.direction = Direction.S;
				break;
			case Direction.W:
				this.direction = Direction.N;
				break;
			case Direction.SE:
				this.direction = Direction.SW;
				break;
			case Direction.SW:
				this.direction = Direction.NW;
				break;
			case Direction.NE:
				this.direction = Direction.SE;
				break;
			case Direction.NW:
				this.direction = Direction.NE;
				break;
			default:
				break;
			}
			break;

		default:
			this.direction = direction;
			break;
		}
	}

	public void do_pick(String direction) {
		ArrayList<Entity> listE = (ArrayList<Entity>) parent.qt.getEntitiesFromRadius(x, y, reach);
		for (Entity e : listE) {
			if (pickable.contains(e.name)) {
				if (direction == null) {
					spawnSpiral(e);
					return;
				}
				do_turn(direction);
				if (isEntityInDirection(e, this.direction)) {
					spawnSpiral(e);
					return;
				}
			}
		}
	}

	public void do_die() {
		parent.qt.remove(this);
		parent = null;
	}

	public void spawnSpiral(Entity e) {
		int x = dest.size / 2;
		int y = dest.size / 2;

		int[][] directions = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
		int cpt = 1;

		if (dest.qt.getEntitiesFromRadius(x, y, hitbox).isEmpty()) {
			parent.qt.remove(e);
			e.x = x;
			e.y = y;
			dest.qt.insert(e);
			e.parent = dest;
			return;
		}

		while (cpt < dest.size * 2) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cpt; j++) {
					x += directions[i][0] * hitbox * 0.5;
					y += directions[i][1] * hitbox * 0.5;
					if (dest.qt.bdr.inBoundary(x, y)) {
						if (dest.qt.getEntitiesFromRadius(x, y, hitbox).isEmpty()) {
							parent.qt.remove(e);
							e.x = x;
							e.y = y;
							dest.qt.insert(e);
							e.parent = dest;
							return;
						}
					}
				}
			}
			cpt++;

			for (int i = 2; i < 4; i++) {	
				for (int j = 0; j < cpt; j++) {
					x += directions[i][0] * hitbox * 0.5;
					y += directions[i][1] * hitbox * 0.5;
					if (dest.qt.bdr.inBoundary(x, y)) {
						if (dest.qt.getEntitiesFromRadius(x, y, hitbox).isEmpty()) {
							parent.qt.remove(e);
							e.x = x;
							e.y = y;
							dest.qt.insert(e);
							e.parent = dest;
							return;
						}
					}
				}
			}
			cpt++;
		}
	}

	public abstract void do_get();

	// public abstract void do_store();

	public abstract void do_hit(String direction);


	public abstract void do_wait();

	public abstract void do_paint(Graphics g, int width, int height, Player p);
	
	public abstract void do_egg(String direction, String category);


	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

	public void update(long elasped) {
		this.elasped += elasped;
		if (this.elasped > 70) {
			if (this instanceof Player) {
				((Player) this).isRunning = false;
			}
			fsm.step(this);
			this.elasped = 0;
		}

	}
}
