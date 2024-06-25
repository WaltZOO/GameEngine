package model;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class World {
	int size;
	QuadTree qt;
	BufferedImage background;
	int hitbox;
	String name;
	int Entity_Cap;
	boolean chargedIfNoPlayer;

	static final boolean debug = true;

	public World(int hitbox, String name) {
		size = 0;
		this.hitbox = hitbox;
		this.name = name;
	}

	public World(String name) {
		size = 0;
		this.name = name;
	}

	public World(int size, String filename, int hitbox, String name, int Entity_Cap, boolean chargedIfNoPlayer)
			throws IOException {
		this.hitbox = hitbox;
		this.qt = new QuadTree(0, hitbox, new Boundary(0, 0, size, size));
		this.size = size;
		this.name = name;
		this.chargedIfNoPlayer = chargedIfNoPlayer;
		this.Entity_Cap = Entity_Cap;

		if (filename != null) {
			File file = new File(filename);
			if (!file.exists()) {
				System.out.println("Fichier " + filename + " introuvable");
			}
			background = ImageIO.read(file);
		}

	}

	public String getName() {
		return name;
	}

	public void update(long elasped) {
		ArrayList<Entity> liste = (ArrayList<Entity>) qt.updateEntities();
		for (Entity e : liste) {
			e.update(elasped);
		}
	}

	public void random_insert(Entity e) {
		int x = (int) (Math.random() * size);
		int y = (int) (Math.random() * size);
		while (!qt.getEntitiesFromRadius(x, y, hitbox * 2).isEmpty()) { // on verifie que l'on peut insérer a ces
																		// coordonées
			x = (int) (Math.random() * size);
			y = (int) (Math.random() * size);
		}
		e.x = x;
		e.y = y;
		qt.insert(e);

	}

	public void do_paint(Graphics g, int width, int height, Player P) {

		// on multiplie d'abord la range par 2 car si on faisait l'inverse on
		// augmenterait la scale
		// 2 par ce que c'est la moitié de l'écran
		// * P.range car nous voulons la divisé par le range du joueur
		float scale = (float) height / P.range;

		// -P.x * scale1
		// - pour le dessiner en haut à gauche
		// P.x * scale1 ici on multiplie les coordonées du joueur par la scale pour
		// avoir l'offset
		// + width/4 pour ajuster au milieu de la premiere moitié de l'écran
		// ex : width = 800 , P.range = 100 , P.x = 50, size = 100
		// L> scale = 800/(2*100) = 4.0
		// L> xoffset = -50*4 + 200 = 0
		// L> size * scale = 100 * 2 = 200
		// Le joueur est placé en 100 dans le canvas (200*50/100)
		// ex : width = 800 , P.range = 200 , P.x = 300, size = 400
		// L> scale = 800/(2*200) = 2.0
		// L> xoffset = -300*2 + 200 = -400
		// L> size * scale = 400 * 2 = 800
		// Le joueur est placé en 600 (300*800/400)

		int xOffset1 = (int) (-P.x * scale + width / 4);
		int yOffset1 = (int) (-P.y * scale + height / 2);

		int sizex = (int) ((float) size * scale);

		if (P.isPlayer1) {
			g.setClip(0, 0, width / 2, height);
			g.drawImage(background, xOffset1, yOffset1, sizex, sizex, null);
		} else {
			g.setClip(width / 2, 0, width / 2, height);
			g.drawImage(background, xOffset1 + width / 2, yOffset1, sizex, sizex, null);
		}

		// On affiche les entités du monde
		List<Entity> listE = qt.getEntitiesFromRadius(P.x, P.y, P.range);
		for (Entity e : listE) {
			e.do_paint(g, width, height, P);
		}

		// on affiche les quadtree seulement en mode débug
		if (debug) {
			qt.paint(g, width, height, P);
		}
	}
}
