package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
	JSONObject jo;

	List<String> blocs_string = new ArrayList<String>();
	List<String> worlds_string = new ArrayList<String>();
	List<String> players_string = new ArrayList<String>();
	List<String> npcs_string = new ArrayList<String>();

	public JSONReader(String Filename) throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(Filename));
		this.jo = (JSONObject) obj;

		// on récupère les entités
		Set<String> keys = jo.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);

		for (int i = 0; i < keysArray.length; i++) {
			String key = keysArray[i];
			Object value = jo.get(key);
			if (!value.toString().contains("sprite") && !value.toString().contains("FSM")) {
				if (value instanceof JSONObject)
					worlds_string.add(key);
			} else if (!value.toString().contains("CanRespawn") && !value.toString().contains("hp")
					&& value instanceof JSONObject) {
				blocs_string.add(key);
			} else if (!value.toString().contains("CanRespawn") && value instanceof JSONObject) {
				npcs_string.add(key);
			} else if (value instanceof JSONObject) {
				players_string.add(key);
			}
		}
	}

	public Double getSeed() {
		Number tmp = (Number) jo.get("seed");
		return tmp.doubleValue();
	}

	public Double getTimer() {
		Number tmp = (Number) jo.get("timer");
		return tmp.doubleValue();
	}

	public int getHitbox() {
		Number tmp = (Number) jo.get("hitbox");
		return tmp.intValue();
	}

	public ArrayList<Bloc> getBlocs() throws IOException { // Récupération des blocs reconnus
		ArrayList<Bloc> blocs = new ArrayList<Bloc>();
		System.out.println("Blocs :");
		for (int i = 0; i < blocs_string.size(); i++) {
			String bloc = blocs_string.get(i);
			System.out.println(blocs_string);
			JSONObject blocDetails = (JSONObject) jo.get(bloc);
			JSONArray position = (JSONArray) blocDetails.get("position");
			String name = (String) blocDetails.get("name");
			Number reach = (Number) blocDetails.get("reach");
			Number size = (Number) blocDetails.get("size");
			String fsm = (String) blocDetails.get("FSM");
			String sprite = (String) blocDetails.get("sprite");
			String direction = (String) blocDetails.get("direction");
			String dest = (String) blocDetails.get("dest");
			JSONArray pickableArray = (JSONArray) blocDetails.get("pickable");
			Number speed = (Number) blocDetails.get("speed");

			ArrayList<String> pickable = jsonArrayToStringList(pickableArray);
			World world_dest = new World();
			blocs.add(new Bloc(((Long) position.get(0)).intValue(), ((Long) position.get(1)).intValue(),
					speed.intValue(), direction, reach.intValue(), world_dest, sprite, pickable, name, fsm, new World()));
		}
		return blocs;
	}

	public ArrayList<NPC> getNpcs() throws IOException { // Récupération des blocs reconnus
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		for (int i = 0; i < npcs_string.size(); i++) {
			String NPC = npcs_string.get(i);
			System.out.println(npcs_string);
			JSONObject NPCDetails = (JSONObject) jo.get(NPC);
			JSONArray position = (JSONArray) NPCDetails.get("position");
			String name = (String) NPCDetails.get("name");
			Number reach = (Number) NPCDetails.get("reach");
			Number size = (Number) NPCDetails.get("size");
			String fsm = (String) NPCDetails.get("FSM");
			String sprite = (String) NPCDetails.get("sprite");
			String direction = (String) NPCDetails.get("direction");
			Number speed = (Number) NPCDetails.get("speed");
			Number damage = (Number) NPCDetails.get("damage");
			Number hp = (Number) NPCDetails.get("hp");
			String dest = (String) NPCDetails.get("dest");
			JSONArray pickableArray = (JSONArray) NPCDetails.get("pickable");
			String team = (String) NPCDetails.get("team");
			Number range = (Number) NPCDetails.get("range");
			JSONArray alliesArray = (JSONArray) NPCDetails.get("allies");
			JSONArray enemiesArray = (JSONArray) NPCDetails.get("enemies");

			ArrayList<String> pickable = jsonArrayToStringList(pickableArray);
			ArrayList<String> allies = jsonArrayToStringList(alliesArray);
			ArrayList<String> enemies = jsonArrayToStringList(enemiesArray);
			World world_dest = new World();

			npcs.add(new NPC(((Long) position.get(0)).intValue(), ((Long) position.get(1)).intValue(), speed.intValue(),
					direction, reach.intValue(), world_dest, sprite, pickable, team, hp.intValue(), damage.intValue(),
					enemies, allies, range.intValue(), name, fsm, new World()));

		}
		return npcs;
	}

	public ArrayList<Player> getPlayers() throws IOException { // Récupération des players reconnus
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < players_string.size(); i++) {
			String player = players_string.get(i);
			// System.out.println(player);
			JSONObject playerDetails = (JSONObject) jo.get(player);
			JSONArray position = (JSONArray) playerDetails.get("position");
			String name = (String) playerDetails.get("name");
			Number reach = (Number) playerDetails.get("reach");
			Number size = (Number) playerDetails.get("size");
			String fsm = (String) playerDetails.get("FSM");
			String sprite = (String) playerDetails.get("sprite");
			String direction = (String) playerDetails.get("direction");
			boolean can_respawn = (boolean) playerDetails.get("CanRespawn");
			Number speed = (Number) playerDetails.get("speed");
			Number damage = (Number) playerDetails.get("damage");
			Number hp = (Number) playerDetails.get("hp");
			Number range = (Number) playerDetails.get("range");
			JSONArray pickableArray = (JSONArray) playerDetails.get("pickable");
			String dest = (String) playerDetails.get("dest");
			String team = (String) playerDetails.get("team");
			JSONArray alliesArray = (JSONArray) playerDetails.get("allies");
			JSONArray enemiesArray = (JSONArray) playerDetails.get("enemies");

			boolean isPlayer1 = false;
			if (name.equals("Player1"))
				isPlayer1 = true;

			ArrayList<String> pickable = jsonArrayToStringList(pickableArray);
			ArrayList<String> allies = jsonArrayToStringList(alliesArray);
			ArrayList<String> enemies = jsonArrayToStringList(enemiesArray);

			World world_dest = new World();
			ArrayList<Integer> position_int = jsonArrayToIntList(position);
			players.add(new Player(position_int.get(0), position_int.get(1), speed.intValue(), direction,
					reach.intValue(), world_dest, sprite, pickable, team, hp.intValue(), damage.intValue(), enemies,
					allies, range.intValue(), name, isPlayer1, can_respawn, fsm, new World()));

		}
		return players;
	}

	public ArrayList<WorldConfig> getWorldsConfig() throws IOException { // Récupération des mondes reconnus
		ArrayList<WorldConfig> worlds_conf = new ArrayList<WorldConfig>();

		for (int i = 0; i < worlds_string.size(); i++) {
			String world = worlds_string.get(i);
			// System.out.println(worlds_string.get(i));
			JSONObject worldDetails = (JSONObject) jo.get(world);
			String background = (String) worldDetails.get("background");
			String parent = (String) worldDetails.get("Parent");
			JSONArray size = (JSONArray) worldDetails.get("size");
			JSONArray world_entities = (JSONArray) worldDetails.get("entities");

			ArrayList<String> categories = new ArrayList<String>();
			ArrayList<Double> densities = new ArrayList<Double>();
			for (int j = 0; j < world_entities.size(); j++) {
				JSONObject current = (JSONObject) world_entities.get(j);
				String entity_type = (String) current.get("type");
				Number entity_density = (Number) current.get("density");
				categories.add(entity_type);
				densities.add(entity_density.doubleValue());
			}
			ArrayList<Integer> size_int = jsonArrayToIntList(size);
			worlds_conf.add(new WorldConfig(new World(size_int.get(0), background), categories, densities));
		}
		return worlds_conf;
	}

	private static ArrayList<String> jsonArrayToStringList(JSONArray jsonArray) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			list.add((String) jsonArray.get(i));
		}
		return list;
	}

	private static ArrayList<Integer> jsonArrayToIntList(JSONArray jsonArray) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			// Convert Long to Integer explicitly
			list.add(((Long) jsonArray.get(i)).intValue());
		}
		return list;
	}
}
