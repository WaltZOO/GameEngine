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

    public void do_paint(Graphics g, int height, int width) {
        if (p1 != null) {
            int xOffset = p1.x - size / 2;
            int yOffset = p1.y - size / 2;
            g.drawImage(background, xOffset, yOffset, size, size, null);
            for (Entity e : listE) {
                e.do_paint(g, height, width);
            }
        }
        if (p2 != null) {
            int xOffset = p2.x - size / 2;
            int yOffset = p2.y - size / 2;
            g.drawImage(background, xOffset, yOffset, size, size, null);
            for (Entity e : listE) {
                e.do_paint(g, height, width);
            }
        }
    }
}
