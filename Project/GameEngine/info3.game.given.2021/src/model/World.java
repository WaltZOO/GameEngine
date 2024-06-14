package model;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
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

			int xOffset = -p1.x * p1.x / p1.range + width / 4;
			int yOffset = -p1.y * p1.y / p1.range + height / 2;
			int offsetside = -(width / 4);
			int temp = size * size / p1.range;

			g.setClip(0, 0, width / 2, height);
			g.drawImage(background, xOffset, yOffset, temp, temp, null);

			for (Entity e : listE) {
				if (e == p2) {
					xOffset = p1.x * p1.x / p1.range - p2.x * p2.x / p1.range;
					yOffset = p1.y * p1.y / p1.range - p2.y * p2.y / p1.range;
					e.do_paint(g, width - 2 * xOffset, height - 2 * yOffset, offsetside,p1.range);
				} else {
					e.do_paint(g, width, height, offsetside,p1.range);
				}

			}
		}

		if (p2 != null) {
			int xOffset = -p2.x * p2.x / p2.range + 3 * width / 4;
			int yOffset = -p2.y * p2.y / p2.range + height / 2;
			int offsetside = width / 4;
			int temp = size * size / p2.range;

			g.setClip(width / 2, 0, width / 2, height);
			g.drawImage(background, xOffset, yOffset, temp, temp, null);

			for (Entity e : listE) {
				if (e == p1) {
					xOffset = p2.x * p2.x / p2.range - p1.x * p1.x / p2.range;
					yOffset = p2.y * p2.y / p2.range - p1.y * p1.y / p2.range;
					e.do_paint(g, width - 2 * xOffset, height - 2 * yOffset, offsetside,p2.range);
				} else {
					e.do_paint(g, width, height, offsetside,p2.range);
				}

			}
			g.setColor(Color.BLACK);
			g.fillRect(width/2-1, 0, 3, height);
		}
		
	}
}
