package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

import ai.Direction;
import ai.FSM;
import ai.State;

public abstract class Entity {
	int x, y; // Position
	String direction;
	int vitesse; // Vitesse pour le Move()
	int hitbox; // Rayon de collisions
	int reach;
	BufferedImage[] sprites;
	int m_imageIndex;

	ArrayList<Entity> pickable;

	// Sprite to add
	FSM fsm;
	State state;
	Monde monde;

	public Entity(int x, int y, int vitesse, int reach, Monde m) {
		this.x = x;
		this.y = y;
		// this.direction = Direction.W;
		this.vitesse = vitesse;
		this.hitbox = 1;
		this.reach = reach;

		this.pickable = new ArrayList<Entity>();

		// this.fsm = new FSM();
		// this.state = new State(1);
		this.monde = m;

		this.m_imageIndex = 0;
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
	
	public boolean eval_key(String key) {
        // TODO
        return false;
    }

    // vraie si l’entité de la Catégorie demandée, la plus proche est dans la
    // Direction
    public boolean eval_closest(String direction, String category) {
        // TODO
        return false;
    }

    // vraie si l'entité est orientée dans la Direction
    public boolean eval_mydir(String direction) {
        return (this.direction == direction);
    }


	public abstract boolean eval_cell(String dir, String cat);

	public abstract void do_move(String direction2);

	public abstract void do_pick(String direction);

	public abstract void do_hit(String direction);

	public abstract void do_store();

	public abstract void do_get();

	public abstract void do_egg(String direction, String category);

	public abstract void do_turn(String direction);

	public abstract void do_wait();


	public abstract void do_paint(Graphics g, int height, int width);

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

}
