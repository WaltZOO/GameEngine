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
			if (!key.toString().equals("Win") && !value.toString().contains("sprite")
					&& !value.toString().contains("FSM")) {
				if (value instanceof JSONObject)
					worlds_string.add(key);
			} else if (!key.toString().equals("Win") && !value.toString().contains("CanRespawn")
					&& !value.toString().contains("hp") && value instanceof JSONObject) {
				blocs_string.add(key);
			} else if (!key.toString().equals("Win") && !value.toString().contains("CanRespawn")
					&& value instanceof JSONObject) {
				npcs_string.add(key);
			} else if (!key.toString().equals("Win") && value instanceof JSONObject) {
				players_string.add(key);
			}
		}
	}

	public Double getSeed() {
		Number tmp = (Number) jo.get("seed");
		return tmp.doubleValue();
	}

	// public Double getTimer() {
	// Number tmp = (Number) jo.get("timer");
	// return tmp.doubleValue();
	// }

	public int getHitbox() {
		Number tmp = (Number) jo.get("hitbox");
		return tmp.intValue();
	}

	private ArrayList<String> parseEntityNames(JSONArray entityArray) {
		ArrayList<String> entityNames = new ArrayList<>();
		for (Object entityName : entityArray) {
			entityNames.add((String) entityName);
		}
		return entityNames;
	}

	public Victory getVictory() {

		ArrayList<WinCondition> winConditions = new ArrayList<>();
		TimerCondition timer_cond = null;
		ArrayList<String> booleanOperations = new ArrayList<String>();

		JSONObject winObject = (JSONObject) jo.get("Win");
		for (Object key : winObject.keySet()) {
			if (key.equals("cond_final")) {
				String condFinal = (String) winObject.get("cond_final");
				String[] parts = condFinal.split(" ");
				for (String part : parts) {
					if (part.equals("||") || part.equals("&&")) {
						booleanOperations.add(part);
					}
				}
			} else {
				JSONObject cond = (JSONObject) winObject.get(key);
				String winMsg = (String) cond.get("win_msg");

				if (key.equals("Timer")) {
					int timer = ((Number) cond.get("timer")).intValue();
					winConditions.add(new TimerCondition(winMsg, timer*1000));
				} else {
					ArrayList<String> entityNames = new ArrayList<>();
					boolean isPresent = false;
					if (cond.containsKey("Entity")) {
						JSONArray entityArray = (JSONArray) cond.get("Entity");
						entityNames = parseEntityNames(entityArray);
						isPresent = Boolean.parseBoolean((String) cond.get("present"));
					}

					String world = null;
					if (cond.containsKey("World")) {
						world = (String) cond.get("World");
					}

					World world_dest = new World(world);
					winConditions.add(new EntityCondition(winMsg, world_dest, entityNames, isPresent));
				}
			}
		}
		Victory v = new Victory(winConditions, booleanOperations, timer_cond);

		return v;
	}

	public ArrayList<Bloc> getBlocs() throws Exception { // Récupération des blocs reconnus
		ArrayList<Bloc> blocs = new ArrayList<Bloc>();
		// System.out.println("Blocs :");
		for (int i = 0; i < blocs_string.size(); i++) {
			String bloc = blocs_string.get(i);
			// System.out.println(blocs_string);
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
			World world_dest = new World(getHitbox(), dest);
			blocs.add(new Bloc(((Long) position.get(0)).intValue(), ((Long) position.get(1)).intValue(),
					speed.intValue(), direction, reach.intValue(), world_dest, sprite, pickable, name, fsm,
					new World(getHitbox(), "")));
		}
		return blocs;
	}

	public ArrayList<NPC> getNpcs() throws Exception { // Récupération des blocs reconnus
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		for (int i = 0; i < npcs_string.size(); i++) {
			String NPC = npcs_string.get(i);
			// System.out.println(npcs_string);
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
			World world_dest = new World(getHitbox(), dest);

			npcs.add(new NPC(((Long) position.get(0)).intValue(), ((Long) position.get(1)).intValue(), speed.intValue(),
					direction, reach.intValue(), world_dest, sprite, pickable, team, hp.intValue(), damage.intValue(),
					enemies, allies, range.intValue(), name, fsm, new World(getHitbox(), "")));

		}
		return npcs;
	}

	public ArrayList<Player> getPlayers() throws Exception { // Récupération des players reconnus
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < players_string.size(); i++) {
			String player = players_string.get(i);
			// //System.out.println(player);
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

			World world_dest = new World(getHitbox(), dest);
			ArrayList<Integer> position_int = jsonArrayToIntList(position);
			players.add(new Player(position_int.get(0), position_int.get(1), speed.intValue(), direction,
					reach.intValue(), world_dest, sprite, pickable, team, hp.intValue(), damage.intValue(), enemies,
					allies, range.intValue(), name, isPlayer1, can_respawn, fsm, new World(getHitbox(), "")));

		}
		return players;
	}

	public ArrayList<WorldConfig> getWorldsConfig() throws IOException { // Récupération des mondes reconnus
		ArrayList<WorldConfig> worlds_conf = new ArrayList<WorldConfig>();

		for (int i = 0; i < worlds_string.size(); i++) {
			String world = worlds_string.get(i);
			// //System.out.println(worlds_string.get(i));
			JSONObject worldDetails = (JSONObject) jo.get(world);
			String background = (String) worldDetails.get("background");
			String parent = (String) worldDetails.get("Parent");
			JSONArray size = (JSONArray) worldDetails.get("size");
			JSONArray world_entities = (JSONArray) worldDetails.get("entities");
			boolean isLoaded = (boolean) Boolean.parseBoolean((String)worldDetails.get("isLoaded"));
			Number max_entities = (Number) worldDetails.get("max_entwities");
			if(max_entities == null)
				max_entities = 0;

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
			worlds_conf.add(
					new WorldConfig(new World(size_int.get(0), background, getHitbox(), world,  max_entities.intValue(), isLoaded), categories, densities));
		}
		return worlds_conf;
	}

	private static ArrayList<String> jsonArrayToStringList(JSONArray jsonArray) {
		ArrayList<String> list = new ArrayList<>();
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				list.add((String) jsonArray.get(i));
			}
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
