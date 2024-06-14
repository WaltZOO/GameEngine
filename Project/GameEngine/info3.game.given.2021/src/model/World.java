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
	BufferedImage background;
	Player p1 = null;
	Player p2 = null;

	public World(int size, String filename, Player p1, Player p2) throws IOException {
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

			int xOffset = -p1.x * p1.x / p1.fov + width / 4;
			int yOffset = -p1.y * p1.y / p1.fov + height / 2;
			int offsetside = -(width / 4);
			int temp = size * size / p1.fov;

			g.setClip(0, 0, width / 2, height);
			g.drawImage(background, xOffset, yOffset, temp, temp, null);

			for (Entity e : listE) {
				if (e == p2) {
					xOffset = p1.x * p1.x / p1.fov - p2.x * p2.x / p1.fov;
					yOffset = p1.y * p1.y / p1.fov - p2.y * p2.y / p1.fov;
					e.do_paint(g, width - 2 * xOffset, height - 2 * yOffset, offsetside,p1.fov);
				} else {
					e.do_paint(g, width, height, offsetside,p1.fov);
				}

			}
		}

		if (p2 != null) {
			int xOffset = -p2.x * p2.x / p2.fov + 3 * width / 4;
			int yOffset = -p2.y * p2.y / p2.fov + height / 2;
			int offsetside = width / 4;
			int temp = size * size / p2.fov;

			g.setClip(width / 2, 0, width / 2, height);
			g.drawImage(background, xOffset, yOffset, temp, temp, null);

			for (Entity e : listE) {
				if (e == p1) {
					xOffset = p2.x * p2.x / p2.fov - p1.x * p1.x / p2.fov;
					yOffset = p2.y * p2.y / p2.fov - p1.y * p1.y / p2.fov;
					e.do_paint(g, width - 2 * xOffset, height - 2 * yOffset, offsetside,p2.fov);
				} else {
					e.do_paint(g, width, height, offsetside,p2.fov);
				}

			}
			g.setColor(Color.BLACK);
			g.fillRect(width/2-1, 0, 3, height);
		}
		
	}
}
