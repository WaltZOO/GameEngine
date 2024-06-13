package model;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Personnage{
	boolean isPlayer1;

	public Player(int x, int y, int vitesse, int reach, Monde m, int vie, int damage, boolean isPlayer1) {
		super(x, y, vitesse, reach, m, vie, damage);
		this.isPlayer1=isPlayer1;
	}

	@Override
	public void do_paint(Graphics g,int height,int width) {
		//BufferedImage img = sprites[m_imageIndex];
		//g.drawImage(img, x, y, scale * img.getWidth(), scale * img.getHeight(), null);
		g.setColor(Color.BLUE);
		g.drawOval(width/2, height/2, hitbox, hitbox);
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
	public void do_store() {
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
	public boolean eval_cell(String dir, String cat) {
		// TODO Auto-generated method stub
		return false;
	}
}
