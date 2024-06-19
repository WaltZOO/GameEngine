package model;

import java.util.ArrayList;
import java.util.List;

public class test_parserJSON {

	public static void main(String[] args) throws Exception {
		JSONReader JP = new JSONReader("./resources/configjeu1_mvpScom.json");
		double timer = JP.getTimer();
		System.out.println(timer);
		double seed = JP.getSeed();
		System.out.println(seed);
		double hitbox = JP.getHitbox();
		System.out.println(hitbox);
		List<WorldConfig> worlds_conf = JP.getWorldsConfig();
		int nworlds = worlds_conf.size();
		System.out.println(nworlds);

		Player tmp = JP.getPlayers().get(0);
		Player p1 = tmp;
		Player p2 = JP.getPlayers().get(1);

		ArrayList<NPC> npcs = JP.getNpcs();
		ArrayList<Bloc> blocs = JP.getBlocs();
		
	}

}
