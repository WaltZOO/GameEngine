package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import ai.Direction;

public class Model {
	int timer;
	int seed;
	int nMonde;
	List<World> mondes;
	Player P1;
	Player P2;

	public Model(int timer, int seed) {
		this.timer = timer;
		this.seed = seed;
		this.mondes = new ArrayList<World>();

	}
	/*
	public static void main(String args[]) throws IOException, ParseException {
		Model m = new Model(0,0);
		m.Init_Game();
		m.mondes.get(0).qt.AffichageProfondeur();
		m.mondes.get(0).listE.get(0).do_pick(Direction.W);
		m.mondes.get(0).qt.AffichageProfondeur();
		m.mondes.get(1).qt.AffichageProfondeur();
	}
	*/

	public void Init_Game() throws IOException, ParseException {
		// JSONReader JP = new JSONReader();
		// JP.parseConfig();

		Player P1 = new Player(500, 500, 20, null, 100, 20, null, null, "resources/j1.jpg", null, null, 20, 100, null,
				null, 500, null, true, true);
		Player P2 = new Player(550, 510, 20, null, 30, 20, null, null, "resources/j2.jpg", null, null, 30, 100, null,
				null, 200, null, false, true);

		this.P1 = P1;
		this.P2 = P2;
	
		World monde = new World(1000, "resources/background.png");
		P1.parent = monde;
		P2.parent = monde;
		mondes.add(monde);

		for (int i = 0; i < 1; i++) {

			int xrand = (int) (Math.random() * monde.size);
			int yrand = (int) (Math.random() * monde.size);
			Character c = new Character(xrand, yrand, 20, null, 30, 20, null, null, "resources/j1.jpg", null, null, 20,100, null, null, 300, null);
			monde.listE.add(c);
			monde.qt.insert(c);
		}

		monde.listE.add(P1);
		monde.listE.add(P2);
		monde.qt.insert(P1);
		monde.qt.insert(P2);

	}

	public void update() {
		if (P1 != null) {
			if (P1.parent != null) {
				if (P1.x < P1.parent.size)
					P1.x += 1;
			}
		}

	}

	public void paint(Graphics g, int width, int height) {
		P1.parent.do_paint(g, width, height, P1);
		P2.parent.do_paint(g, width, height, P2);
	}
}
