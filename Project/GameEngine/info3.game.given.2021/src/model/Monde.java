package model;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monde {
	int size;
	List<Entity> listE;
	BufferedImage background;
	Player p1 = null;
	Player p2 = null;

	public Monde(int size, String filename, Player p1, Player p2) throws IOException {
		listE = new ArrayList<Entity>();
		this.size = size;

		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("Fichier " + filename + " introuvable");
		}
		background = ImageIO.read(file);

		this.p1 = p1;
		this.p2 = p2;
	}

	public void do_paint(Graphics g, int width, int height) {
		if (p1 != null) {
			int xOffset = -p1.x + width / 4;
			int yOffset = -p1.y + height / 2;
			int offsetside = -(width / 4);

			g.setClip(0, 0, width / 2, height);
			g.drawImage(background, xOffset, yOffset, size, size, null);

			for (Entity e : listE) {
				if(e == p2) {
						xOffset = p1.x - p2.x;
						yOffset = p1.y - p2.y;
						e.do_paint(g, width - 2*xOffset , height - 2*yOffset, offsetside);
				}else {
					e.do_paint(g, width, height, offsetside);
				}
				
			}
		}

		if (p2 != null) {
			int xOffset = -p2.x + 3 * width / 4;
			int yOffset = -p2.y + height / 2;
			int offsetside = width / 4;

			g.setClip(width / 2, 0, width / 2, height);
			g.drawImage(background, xOffset, yOffset, size, size, null);

			for (Entity e : listE) {
				if(e == p1) {
						xOffset = p2.x - p1.x;
						yOffset = p2.y - p1.y;
						e.do_paint(g, width - 2*xOffset , height - 2*yOffset, offsetside);
				}else {
					e.do_paint(g, width, height, offsetside);
				}
				
			}
		}
	}
}
