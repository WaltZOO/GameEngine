package model;

import java.awt.Graphics;
import java.util.ArrayList;

public class NPC extends Character {

	public NPC(int x, int y, int speed, String direction, int reach, World dest,
			String filename, ArrayList<String> pickable, String team, int hp, int damage, ArrayList<String> ennemies,
			ArrayList<String> allies, int range, String name, String fsm, World parent) throws Exception {
		super(x, y, speed, direction, reach, dest, filename, pickable, team, hp, damage, ennemies, allies,
				range, name, fsm, parent);
		// TODO Auto-generated constructor stub
	}
	public NPC(NPC other) throws Exception {
        super(other.x, other.y, other.speed, other.direction, other.reach, other.dest, other.sprites, 
              new ArrayList<>(other.pickable), other.team, other.hp, other.damage, 
              new ArrayList<>(other.ennemies), new ArrayList<>(other.allies), 
              other.range, other.name, other.fsm, other.parent);
    }



	@Override
	public void do_move(String direction2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_pick(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_hit(String direction) {
		// TODO Auto-generated method stub

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
	public void do_turn(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_wait() {
		// TODO Auto-generated method stub

	}

	@Override
	public void do_paint(Graphics g, int width, int height, Player p) {
		super.do_paint(g, width, height, p);
	}

}
