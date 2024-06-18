package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class Model {
	double timer;
	double seed;
	double hitbox;
	int nworlds;
	List<World> worlds;
	Player p1;
	Player p2;

	public Model() {
		this.timer = 0;
		this.seed = 0;
		this.hitbox = 0;
		this.nworlds = 0;
		this.worlds = new ArrayList<World>();
		Player p1 = null;
		Player p2 = null;
	}

	public Model(JSONReader JP) throws IOException {
		
		this.timer = JP.getTimer();
		this.seed = JP.getSeed();
		this.hitbox = JP.getHitbox();
		List<WorldConfig> worlds_conf = JP.getWorldsConfig();		
		this.nworlds = worlds_conf.size();
		
		Player tmp = JP.getPlayers().get(0);
		if (tmp.isPlayer1) {
			p1 = tmp;
			p2 = JP.getPlayers().get(1);
		}
		
		
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
		for (World m : worlds) {
			m.do_paint(g, height, width);
		}
	}
}
