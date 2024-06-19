package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	final int Entity_Cap = 4;
	int level = 0;
	int hitbox;
	List<Entity> nodes;

	QuadTree NW = null;
	QuadTree NE = null;
	QuadTree SW = null;
	QuadTree SE = null;

	Boundary bdr;

	public QuadTree(int level, int hitbox, Boundary bdr) {
		this.level = level;
		this.hitbox = hitbox;
		this.bdr = bdr;
		this.nodes = new ArrayList<Entity>();
	}

	public void AffichageProfondeur() {
		System.out.printf("\nLevel = %d [X1=%d Y1=%d] \t[X2=%d Y2=%d] ", level, bdr.getxMin(), bdr.getyMin(),
				bdr.getxMax(), bdr.getyMax());

		for (Entity e : nodes) {
			System.out.printf(" \n\t  x=%d y=%d", e.x, e.y);
		}
		if (nodes.size() == 0) {
			System.out.printf(" \n\t  Leaf Node.");
		}
		if (NW == null)
			return;

		NW.AffichageProfondeur();
		NE.AffichageProfondeur();
		SW.AffichageProfondeur();
		SE.AffichageProfondeur();
	}

	public void split() {
		int xOffset = bdr.getxMin() + (bdr.getxMax() - bdr.getxMin()) / 2;
		int yOffset = bdr.getyMin() + (bdr.getyMax() - bdr.getyMin()) / 2;

		NW = new QuadTree(level + 1, hitbox, new Boundary(bdr.getxMin(), bdr.getyMin(), xOffset, yOffset));
		NE = new QuadTree(level + 1, hitbox, new Boundary(xOffset, bdr.getyMin(), bdr.getxMax(), yOffset));
		SW = new QuadTree(level + 1, hitbox, new Boundary(bdr.getxMin(), yOffset, xOffset, bdr.getyMax()));
		SE = new QuadTree(level + 1, hitbox, new Boundary(xOffset, yOffset, bdr.getxMax(), bdr.getyMax()));

		int size = nodes.size();
		for (int i = 0; i < size; i++) {
			insert(nodes.remove(0));
		}
	}

	public void insert(Entity e) {
		int x = e.x;
		int y = e.y;

		if (!bdr.inBoundary(x, y))
			return;

		// Si l'entity cap n'est pas dépassé
		if ((hitbox * Entity_Cap >= bdr.getxMax() - bdr.getxMin()
				|| hitbox * Entity_Cap >= bdr.getyMax() - bdr.getyMin() || nodes.size() < Entity_Cap) && NW == null) {
			nodes.add(e);
			return;
		}

		// Si l'entity cap est dépassé et qu'il n y a pas de sous-QuadTree
		if (NW == null) {
			this.split();
		}

		// Insertion dans le bon sous-QuadTree
		if (NW.bdr.inBoundary(x, y))
			NW.insert(e);

		else if (NE.bdr.inBoundary(x, y))
			NE.insert(e);

		else if (SW.bdr.inBoundary(x, y))
			SW.insert(e);

		else if (SE.bdr.inBoundary(x, y))
			SE.insert(e);

	}

	public void fusion() {
		if (NW == null && NE == null && SW == null && SE == null)
			return;

		if (NW.nodes.size() + NE.nodes.size() + SW.nodes.size() + SE.nodes.size() > Entity_Cap)
			return;

		if (NW != null) {
			nodes.addAll(NW.nodes);
			NW = null;
		}
		if (NE != null) {
			nodes.addAll(NE.nodes);
			NE = null;
		}
		if (SW != null) {
			nodes.addAll(SW.nodes);
			SW = null;
		}
		if (SE != null) {
			nodes.addAll(SE.nodes);
			SE = null;
		}

	}

	public void remove(Entity e) {
		int x = e.x;
		int y = e.y;

		if (!bdr.inBoundary(x, y))
			return;

		if (NW == null) {
			nodes.remove(e);
			return;
		}

		if (NW.bdr.inBoundary(x, y))
			NW.remove(e);

		else if (NE.bdr.inBoundary(x, y))
			NE.remove(e);

		else if (SW.bdr.inBoundary(x, y))
			SW.remove(e);

		else if (SE.bdr.inBoundary(x, y))
			SE.remove(e);

		if (NW.nodes.size() + NE.nodes.size() + SW.nodes.size() + SE.nodes.size() <= Entity_Cap)
			if (NW.NW == null && NE.NE == null && SW.SW == null && SE.SE == null)
				this.fusion();
	}

	public List<Entity> getEntitiesFromRadius(int x, int y, int r) {
		ArrayList<Entity> listE = new ArrayList<Entity>();

		if (!bdr.intersect(x, y, r))
			return listE;

		if (NW == null) {
			for (Entity e : nodes) {
				int distX = x - e.x;
				int distY = y - e.y;
				if (r * r >= distX * distX + distY * distY)
					listE.add(e);
			}
			return listE;

		} else {

			listE.addAll(NW.getEntitiesFromRadius(x, y, r));
			listE.addAll(NE.getEntitiesFromRadius(x, y, r));
			listE.addAll(SW.getEntitiesFromRadius(x, y, r));
			listE.addAll(SE.getEntitiesFromRadius(x, y, r));
			return listE;
		}
	}

	/*
	 * public QuadTree getQuadTree(Entity e) { int x = e.x; int y = e.y;
	 * 
	 * if (!bdr.inBoundary(x, y)) return null; if (NW == null) { return this; } if
	 * (NW.bdr.inBoundary(x, y)) return NW.getQuadTree(e);
	 * 
	 * else if (NE.bdr.inBoundary(x, y)) return NE.getQuadTree(e);
	 * 
	 * else if (SW.bdr.inBoundary(x, y)) return SW.getQuadTree(e);
	 * 
	 * else if (SE.bdr.inBoundary(x, y)) return SE.getQuadTree(e); else return null;
	 * }
	 */

	/*
	 * public static void main(String args[]) { QuadTree T = new QuadTree(0, 100,new
	 * Boundary(0, 0, 1000, 1000)); T.insert(new Player(100, 100, 0, 0, 40, 1, null,
	 * 0, 0, false)); T.insert(new Player(490, 490, 0, 0, 40, 1, null, 0, 0,
	 * false)); T.insert(new Player(200, 800, 0, 0, 40, 1, null, 0, 0, false));
	 * T.insert(new Player(50, 900, 0, 0, 40, 1, null, 0, 0, false)); T.insert(new
	 * Player(400, 600, 0, 0, 40, 1, null, 0, 0, false)); Player P1 = new
	 * Player(300, 600, 0, 40, 0, 1, null, 0, 0, false); Player P2 = new Player(350,
	 * 650, 0, 40, 0, 1, null, 0, 0, false); T.insert(P1); T.insert(P2);
	 * T.insert(new Player(450, 700, 0, 0, 40, 1, null, 0, 0, false)); T.insert(new
	 * Player(400, 700, 0, 0, 40, 1, null, 0, 0, false));
	 * 
	 * T.insert(new Player(400, 100, 0, 0, 40, 1, null, 0, 0, false)); T.insert(new
	 * Player(200, 300, 0, 0, 40, 1, null, 0, 0, false)); T.insert(new Player(400,
	 * 400, 0, 0, 40, 1, null, 0, 0, false));
	 * 
	 * // T.getQuadTree(P2).AffichageProfondeur();
	 * 
	 * // T.remove(P1); // T.remove(P2); List<Entity> listE =
	 * T.getEntitiesFromRadius(375, 625, 1000); for (int i = 0; i < listE.size();
	 * i++) { System.out.printf(" \n\t  x=%d y=%d", listE.get(i).x, listE.get(i).y);
	 * }
	 * 
	 * 
	 * T.AffichageProfondeur();
	 * 
	 * }
	 */
	// Update all Entities in the QuadTree*/
	public List<Entity> updateEntities() {
		if (NW == null) {
			return nodes;
		} else {
			ArrayList<Entity> res= new ArrayList<Entity>();
			res.addAll(NW.updateEntities());
			res.addAll(NE.updateEntities());
			res.addAll(SW.updateEntities());
			res.addAll(SE.updateEntities());
			return res;
		}
	}

	public void paint(Graphics g, int width, int height, Player P) {		
		int sizex = bdr.getxMax()-bdr.getxMin();
		int sizey = bdr.getyMax()-bdr.getyMin();

		float scale1 = (float) width / (2 * P.range);

		float xOffset1 = ((float)(bdr.getxMin()-P.x) * scale1 + width / 4);
		float yOffset1 = ((float)(bdr.getyMin()-P.y) * scale1 + height / 2);
		
		//this.AffichageProfondeur();

		if (P.isPlayer1) {
			g.setClip(0, 0, width / 2, height);
			g.drawRect((int) xOffset1,(int) yOffset1, (int) ((float)sizex * scale1), (int) ((float)sizey * scale1));
			
		} else {
			g.setClip(width / 2, 0, width / 2, height);
			g.drawRect((int) xOffset1 + width / 2, (int) yOffset1, (int) ((float)sizex * scale1), (int) ((float)sizey * scale1));
		}
		
		if (NE != null) {
			NE.paint(g, width, height, P);
			NW.paint(g, width, height, P);
			SE.paint(g, width, height, P);
			SW.paint(g, width, height, P);
		}
	}
}
