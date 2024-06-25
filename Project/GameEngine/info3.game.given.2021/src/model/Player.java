package model;

import java.util.ArrayList;

import ai.Category;
import ai.Direction;

public class Player extends Character {
	boolean isPlayer1;
	boolean canRespawn;

	public Player(int x, int y, int speed, String direction, int reach, World dest, String filename,
			ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, boolean isPlayer1, boolean canRespawn, String fsm,
			World parent) throws Exception {

		super(x, y, speed, direction, reach, dest, filename, pickable, team, hp, damage, ennemies, allies, range, name,
				fsm, parent);

		isRunning = false;

		this.isPlayer1 = isPlayer1;
		this.canRespawn = canRespawn;
	}

	public Player(Player other) throws Exception {
		super(other.x, other.y, other.speed, other.direction, other.reach, other.dest, other.sprites,
				new ArrayList<String>(other.pickable), other.team, other.hp, other.damage,
				new ArrayList<String>(other.ennemies), new ArrayList<String>(other.allies), other.range, other.name,
				other.fsm, other.parent);

		this.isPlayer1 = other.isPlayer1;
		this.canRespawn = other.canRespawn;
	}

	@Override
	public void do_die() {
		parent.qt.remove(this);
		if (canRespawn) {
			parent.random_insert(this);
			hp = maxHp;
		} else {
			parent = null;
		}
	}

	@Override
	public void do_get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_egg(String direction, String category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_wait() {
		// TODO Auto-generated method stub
	}

	public void do_throw(String direction, String category) {
		ArrayList<Entity> ListE = (ArrayList<Entity>) dest.qt.updateEntities();
		int dist = hitbox + 1;

		if (direction == null)
			direction = Direction.F;
		if (category == null)
			category = Category.ALL;
		direction = relativeToAbsolue(direction);

		Player temp;

		try {
			temp = new Player(this);
		} catch (Exception e) {
			temp = null;
			e.printStackTrace();
		}

		switch (direction) {
			case Direction.N:
				temp.y -= dist;
				break;

			case Direction.S:
				temp.y += dist;
				break;

			case Direction.W:
				temp.x -= dist;
				break;

			case Direction.E:
				temp.x += dist;
				break;
			default:
				break;
		}

		for (Entity e : ListE) {
			if (pickable.contains(e.name)) {
				if (temp.eval(direction, Category.V, hitbox)) {
					e.parent.qt.remove(e);
					e.x = temp.x;
					e.y = temp.y;
					parent.qt.insert(e);
					e.parent = this.parent;
					return;
				}
			}
		}
	}

}
