package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Model {
	Victory victory;
	double seed;
	int hitbox;
	int nworlds;
	ArrayList<World> worlds;
	public Player p1;
	public Player p2;
	final static float pourcenatge_remplissage = 0.7f;
	boolean isFinished;
	String configFileName;
	ArrayList<Entity> allInstances;

	public Model(String configFileName) throws Exception {
		initGame(configFileName);
	}

	public void initGame(String configFileName) throws Exception {
		this.configFileName = configFileName;
		JSONReader JP = new JSONReader(configFileName);
		this.seed = JP.getSeed();
		this.hitbox = JP.getHitbox();
		List<WorldConfig> worlds_conf = JP.getWorldsConfig();
		this.nworlds = worlds_conf.size();
		allInstances = new ArrayList<Entity>();

		p1 = JP.getPlayers().get(1);
		p2 = JP.getPlayers().get(0);
		int nb_player = 0;

		isFinished = false;

		// on ajoute les mondes au model
		this.worlds = new ArrayList<World>();
		for (WorldConfig w : worlds_conf) {

			World temp = w.world;
			for (int i = 0; i < w.categories.size(); i++) {
				for (Bloc b : JP.getBlocs()) {
					if (b.name.equals(w.categories.get(i))) {
						int quantity;
						if (w.densities.get(i) == -1) {
							quantity = 1;
						} else {
							quantity = (int) (((temp.size * temp.size) / (hitbox * hitbox * 3))
									* pourcenatge_remplissage * w.densities.get(i));
						}
						Bloc tempb = new Bloc(b);
						for (int j = 0; j < quantity; j++) {
							tempb = new Bloc(b);
							tempb.parent = temp;
							for (WorldConfig tempw : worlds_conf) {
								if (tempw.world.name.equals(b.dest.name)) {
									tempb.dest = tempw.world;
									break;
								}
							}
							temp.random_insert(tempb);
						}
						allInstances.add(new Bloc(tempb));

					}

				}
				for (NPC b : JP.getNpcs()) {
					if (b.name.equals(w.categories.get(i))) {
						int quantity;
						if (w.densities.get(i) == -1) {
							quantity = 1;
						} else {
							quantity = (int) (((temp.size * temp.size) / (hitbox * hitbox * 3))
									* pourcenatge_remplissage * w.densities.get(i));
						}
						NPC tempb = new NPC(b);
						for (int j = 0; j < quantity; j++) {
							tempb = new NPC(b);
							tempb.setModel(this);
							tempb.parent = temp;
							for (WorldConfig tempw : worlds_conf) {
								if (tempw.world.name.equals(b.dest.name)) {
									tempb.dest = tempw.world;
									break;
								}
							}
							temp.random_insert(tempb);
						}
						allInstances.add(tempb);

					}

				}
				for (Player b : JP.getPlayers()) {
					if (b.name.equals(w.categories.get(i))) {
						int quantity;
						if (w.densities.get(i) == -1) {
							quantity = 1;
						} else {
							quantity = (int) (((temp.size * temp.size) / (hitbox * hitbox * 3))
									* pourcenatge_remplissage * w.densities.get(i));
						}
						for (int j = 0; j < quantity; j++) {
							Player tempb = new Player(b);
							tempb.parent = temp;
							for (WorldConfig tempw : worlds_conf) {
								if (tempw.world.name.equals(b.dest.name)) {
									tempb.dest = tempw.world;
									break;
								}
							}
							temp.random_insert(tempb);
							if (nb_player++ == 0) {
								p1 = tempb;
							} else {
								p2 = tempb;
							}
						}

					}

				}
			}
			worlds.add(temp);

		}
		this.victory = JP.getVictory();
		if (victory != null) {
			ArrayList<WinCondition> conditions = new ArrayList<WinCondition>();
			conditions = this.victory.getWinConditions();
			for (WinCondition wc : conditions) {
				if (wc instanceof EntityCondition) {
					EntityCondition ec = (EntityCondition) wc;
					String name = ec.getWorld().getName();
					for (World w : worlds) {
						if (w.getName().equals(name))
							ec.w = w;
					}
				}
			}
		}
		System.out.println("Fichier chargé");
	}
	/*
	 * public static void main(String args[]) throws IOException, ParseException {
	 * Model m = new Model(0,0); m.Init_Game();
	 * m.mondes.get(0).qt.AffichageProfondeur();
	 * m.mondes.get(0).listE.get(0).do_pick(Direction.W);
	 * m.mondes.get(0).qt.AffichageProfondeur();
	 * m.mondes.get(1).qt.AffichageProfondeur(); }
	 */

	public void update(long elapsed) {
		if ((this.victory == null || !this.victory.evalCond(elapsed))) {
			for (World m : this.worlds) {
				if (m == p1.parent || m == p2.parent || m.chargedIfNoPlayer)
					m.update(elapsed);
			}
		} else {
			isFinished = true;
		}
	}

	public void paint(Graphics g, int width, int height) {

		if (p1.parent != null) {
			p1.parent.do_paint(g, width, height, p1);

		}
		if (p2.parent != null) {
			p2.parent.do_paint(g, width, height, p2);
		}

		if (isFinished) {
			/*
			 * g.setClip(0, 0, width, height); g.setColor(Color.RED); Font f = new
			 * Font("Arial", 0, 80); g.setFont(f); String message =
			 * this.victory.VictoryMsg(); int offset = width / 2 - f.getSize() *
			 * message.length() / 2 + width / 4 + 10; g.drawString(message, offset, height/2
			 * -100);
			 */
			String[] options = { "Femer", "Rejouer" };

			// Affiche la boîte de dialogue et récupère le choix de l'utilisateur
			int choix = JOptionPane.showOptionDialog(null, this.victory.VictoryMsg(), "Titre de la Boîte de Dialogue",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (choix == 1) {
				try {
					initGame(this.configFileName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.exit(0);
			}
		} else {
			TimerCondition tc = victory.getTimer();
			if (tc != null) {
				g.setClip(0, 0, width, height);
				g.setColor(Color.black);
				Font f = new Font("Arial", 0, 38);
				g.setFont(f);
				String message = Long.toString(tc.getTimer() / 1000L);
				int offset = width / 2 - f.getSize() * message.length() / 2 + width / 100;
				g.drawString(message, offset, 35);
			}
		}

	}
}