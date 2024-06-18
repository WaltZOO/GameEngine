package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReader {
	Number seed = 0;
	Number timer = 0;
	List<Bloc> blocs = new ArrayList<Bloc>();
	List<NPC> npcs = new ArrayList<NPC>();
	List<Player> players = new ArrayList<Player>();
	List<World> worlds = new ArrayList<World>();

	public void parseConfig() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
		Long seed;
		Long timer;
		List<String> entities = new ArrayList<String>();
		List<String> worlds = new ArrayList<String>();
		List<String> players = new ArrayList<String>();
		List<String> monsters = new ArrayList<String>();

		Object obj = new JSONParser().parse(new FileReader("resources/config.json"));
		JSONObject jo = (JSONObject) obj;
		seed = (Long) jo.get("seed");
		timer = (Long) jo.get("timer");
		/*
		 * System.out.println("Seed : " + seed);
		 * System.out.println("Timer : " + timer);
		 */

		// on récupère les entités
		Set<String> keys = jo.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);

		for (int i = 0; i < keysArray.length; i++) {
			String key = keysArray[i];
			Object value = jo.get(key);
			if (!value.toString().contains("sprite") && !value.toString().contains("FSM")) {
				if (value instanceof JSONObject)
					worlds.add(key);
			} else if (!value.toString().contains("CanRespawn") && !value.toString().contains("hp")
					&& value instanceof JSONObject) {
				entities.add(key);
			} else if (!value.toString().contains("CanRespawn") && value instanceof JSONObject) {
				monsters.add(key);
			} else if (value instanceof JSONObject) {
				players.add(key);
			}

		}

		// Affichage des players reconnues
		// System.out.println("Players:");
		for (int i = 0; i < players.size(); i++) {
			String player = players.get(i);
			//System.out.println(player);
			JSONObject playerDetails = (JSONObject) jo.get(player);
			JSONArray position = (JSONArray) playerDetails.get("position");
			String name = (String) playerDetails.get("name");
			Long reach = (Long) playerDetails.get("reach");
			Long size = (Long) playerDetails.get("size");
			String fsm = (String) playerDetails.get("FSM");
			String sprite = (String) playerDetails.get("sprite");
			String direction = (String) playerDetails.get("direction");
			boolean can_respawn = (boolean) playerDetails.get("CanRespawn");
			Number speed = (Number) playerDetails.get("speed");
			Number angle = (Number) playerDetails.get("angle");
			Number damage = (Number) playerDetails.get("damage");
			Number hp = (Number) playerDetails.get("hp");
			Number fov = (Number) playerDetails.get("fov");
			/*
			 * System.out.println("Name: " + name); System.out.println("HP: " +
			 * hp.doubleValue()); System.out.println("Angle: " + angle.doubleValue());
			 * System.out.println("Position: " + position.get(0) + ", " + position.get(1));
			 * System.out.println("Reach: " + reach); System.out.println("Damage: " +
			 * damage.doubleValue()); System.out.println("Size: " + size);
			 * System.out.println("FSM: " + fsm); System.out.println("Speed: " +
			 * speed.doubleValue()); System.out.println("Direction: " + direction);
			 * System.out.println("Sprite: " + sprite); System.out.println("FOV : " +
			 * fov.doubleValue()); System.out.println("Can_respawn : " + can_respawn);
			 * System.out.println();
			 */
		}

		// Affichage des monsters reconnues
		System.out.println("Monsters:");
		for (int i = 0; i < monsters.size(); i++) {
			String monster = monsters.get(i);
			System.out.println(monster);
			JSONObject monsterDetails = (JSONObject) jo.get(monster);
			JSONArray position = (JSONArray) monsterDetails.get("position");
			String name = (String) monsterDetails.get("name");
			Long reach = (Long) monsterDetails.get("reach");
			Long size = (Long) monsterDetails.get("size");
			String fsm = (String) monsterDetails.get("FSM");
			String sprite = (String) monsterDetails.get("sprite");
			String direction = (String) monsterDetails.get("direction");
			Number speed = (Number) monsterDetails.get("speed");
			Number angle = (Number) monsterDetails.get("angle");
			Number damage = (Number) monsterDetails.get("damage");
			Number hp = (Number) monsterDetails.get("hp");

			/*
			 * System.out.println("Name: " + name); System.out.println("HP: " +
			 * hp.doubleValue()); System.out.println("Angle: " + angle.doubleValue());
			 * System.out.println("Position: " + position.get(0) + ", " + position.get(1));
			 * System.out.println("Reach: " + reach); System.out.println("Damage: " +
			 * damage.doubleValue()); System.out.println("Size: " + size);
			 * System.out.println("FSM: " + fsm); System.out.println("Speed: " +
			 * speed.doubleValue()); System.out.println("Direction: " + direction);
			 * System.out.println("Sprite: " + sprite); System.out.println();
			 */
		}

		// Affichage des entités reconnues
		System.out.println("Entities:");
		for (int i = 0; i < entities.size(); i++) {
			String entity = entities.get(i);
			System.out.println(entity);
			JSONObject entityDetails = (JSONObject) jo.get(entity);
			JSONArray position = (JSONArray) entityDetails.get("position");
			String name = (String) entityDetails.get("name");
			Long reach = (Long) entityDetails.get("reach");
			Long size = (Long) entityDetails.get("size");
			String fsm = (String) entityDetails.get("FSM");
			String sprite = (String) entityDetails.get("sprite");
			String direction = (String) entityDetails.get("direction");
			Number angle = (Number) entityDetails.get("angle");

			System.out.println("Name: " + name);
			System.out.println("Angle: " + angle.doubleValue());
			System.out.println("Position: " + position.get(0) + ", " + position.get(1));
			System.out.println("Reach: " + reach);
			System.out.println("Size: " + size);
			System.out.println("FSM: " + fsm);

			System.out.println("Direction: " + direction);
			System.out.println("Sprite: " + sprite);
			System.out.println();
		}

		// Affichage des mondes reconnues
		System.out.println("Worlds:");
		for (int i = 0; i < worlds.size(); i++) {
			String world = worlds.get(i);
			System.out.println(worlds.get(i));
			JSONObject worldDetails = (JSONObject) jo.get(world);
			String background = (String) worldDetails.get("background");
			String parent = (String) worldDetails.get("Parent");
			JSONArray size = (JSONArray) worldDetails.get("size");
			JSONArray world_entities = (JSONArray) worldDetails.get("entities");

			System.out.println("Background : " + background);
			System.out.println("Size: " + size.get(0) + " " + size.get(1));
			System.out.println("Parent : " + parent);

			System.out.println("Entities : ");
			for (int j = 0; j < world_entities.size(); j++) {
				JSONObject current = (JSONObject) world_entities.get(j);
				String entity_type = (String) current.get("type");
				Number entity_density = (Number) current.get("density");
				System.out.println();
				System.out.println("Type: " + entity_type);
				System.out.println("Density: " + entity_density.doubleValue());
				System.out.println();
			}
		}
	}
}
