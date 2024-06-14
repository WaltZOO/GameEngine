package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class Model {
	int timer;
	int seed;
	int nMonde;
	List<World> mondes;

	public Model(int timer, int seed) {
		this.timer = timer;
		this.seed = seed;
		this.mondes = new ArrayList<World>();

	}

	public void Init_Game() throws IOException, ParseException {
		JSONReader JP = new JSONReader();
		JP.parseConfig();
		
		
		/*
		 * Player P1 = new Player(900, 900, 20, 20, 500, 20, null, 100, 10, true);
		 * Player P2 = new Player(950, 910, 20, 50, 400, 30, null, 100, 10, false);
		 * 
		 * Monde monde = new Monde(1000, "resources/background.png", P1, P2);
		 * mondes.add(monde);
		 * 
		 * monde.listE.add(P1); monde.listE.add(P2);
		 */
	}

	public void update() {

	}

	public void paint(Graphics g, int height, int width) {
		for (World m : mondes) {
			m.do_paint(g, height, width);
		}
	}
}
