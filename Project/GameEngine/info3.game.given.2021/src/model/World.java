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
	List<Entity> listE;
	QuadTree qt;
	BufferedImage background;
	int maxHitbox = 100;

	public World(int size, String filename) throws IOException {
		listE = new ArrayList<Entity>();
		qt = new QuadTree(0, maxHitbox, new Boundary(0, 0, size, size));
		this.size = size;

		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("Fichier " + filename + " introuvable");
		}
		background = ImageIO.read(file);
	}

	public void do_paint(Graphics g, int width, int height, Player P) {
		// on multiplie d'abord la range par 2 car si on faisait l'inverse on
		// augmenterait la scale
		// 2 par ce que c'est la moitié de l'écran
		// * P.range car nous voulons la divisé par le range du joueur
		float scale1 = (float) width / (2 * P.range);

		// -P.x * scale1
		// - pour le dessiner en haut à gauche
		// P.x * scale1 ici on multiplie les coordonées du joueur par la scale pour
		// avoir l'offset
		// + width/4 pour ajuster au milieu de la premiere moitié de l'écran
		// ex : width = 800 , P.range = 200 , P.x = 100
		// L> scale = 800/(2*200) = 2.0
		// L> xoffset = -100*2 + 200 = 0
		// L> size * scale = 1000 * 2 = 2000
		// Le joueur est placé en 200 (2000*100/1000)
		// ex : width = 800 , P.range = 200 , P.x = 300
		// L> scale = 800/(2*200) = 2.0
		// L> xoffset = -300*2 + 200 = -400
		// L> size * scale = 1000 * 2 = 2000
		// Le joueur est placé en 600 (2000*300/1000)
		//

		int xOffset1 = (int) (-P.x * scale1 + width / 4);
		int yOffset1 = (int) (-P.y * scale1 + height / 2);
		
		if (P.isPlayer1) {
			g.setClip(0, 0, width / 2, height);
			g.drawImage(background, xOffset1, yOffset1, (int) (size * scale1),
				(int) (size * scale1), null);
		}else {
			g.setClip(width / 2, 0, width / 2, height);
			g.drawImage(background, xOffset1 + width/2, yOffset1 + width/2, (int) (size * scale1),
				(int) (size * scale1), null);
		}
	
		listE = qt.getEntitiesFromRadius(P.x, P.y, P.range);
		for (Entity e : listE) {
			e.do_paint(g, width, height, P);
		}
	}
}
