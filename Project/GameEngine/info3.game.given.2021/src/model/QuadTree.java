package model;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	final int Entity_Cap = 4;
	int level = 0;
	List<Node> nodes;

	QuadTree NW = null;
	QuadTree NE = null;
	QuadTree SW = null;
	QuadTree SE = null;

	Boundary bdr;

	public QuadTree(int level, Boundary bdr) {
		this.level = level;
		this.nodes = new ArrayList<Node>();
		this.bdr = bdr;
	}

	public void ParcoursProfondeur(QuadTree t) {
		if (t == null) {
			return;
		}

		System.out.print("");
	}

	public void split() {
		int xOffset = bdr.getxMin() + (bdr.getxMax() - bdr.getxMin()) / 2;
		int yOffset = bdr.getyMin() + (bdr.getyMax() - bdr.getyMin()) / 2;

		NW = new QuadTree(this.level + 1, new Boundary(bdr.getxMin(), bdr.getyMin(), xOffset, yOffset));
		NE = new QuadTree(this.level + 1, new Boundary(xOffset, bdr.getyMin(), bdr.getxMax(), yOffset));
		SW = new QuadTree(this.level + 1, new Boundary(bdr.getxMin(), yOffset, xOffset, bdr.getyMax()));
		SE = new QuadTree(this.level + 1, new Boundary(xOffset, yOffset, bdr.getxMax(), bdr.getyMax()));
	}

	public void insert(int x, int y, Entity e) {
		if (!bdr.inBoundary(x, y))
			return;
		
		Node node = new Node(x, y, e);
		// Si l'entity cap n'est pas dépassé
		if(nodes.size() < Entity_Cap) {
			nodes.add(node);
			return;
		}
		
		//Si l'entity cap est dépassé et qu'il n y a pas de sous-QuadTree
		if(NW == null) {
			this.split();
		}
		
		//Insertion dans le bon sous-QuadTree
		if(NW.bdr.inBoundary(x, y))
				NW.insert(x, y, e);
		
		else if(NE.bdr.inBoundary(x, y))
			NE.insert(x, y, e);
		
		else if(SW.bdr.inBoundary(x, y))
			SW.insert(x, y, e);
		
		else if(SE.bdr.inBoundary(x, y))
			SE.insert(x, y, e);
		
	}
}
