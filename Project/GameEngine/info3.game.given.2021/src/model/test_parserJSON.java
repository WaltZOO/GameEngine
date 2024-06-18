package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class test_parserJSON {

	public static void main(String[] args) throws IOException, ParseException {
		JSONReader JP = new JSONReader("/home/erwanponcin/Documents/Cours/PLE/g2/Project/Contrat/jeu1/configjeu1_mvpScom.json");
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
