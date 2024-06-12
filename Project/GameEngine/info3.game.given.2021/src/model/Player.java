package model;

public class Player extends Personnage{
	boolean isPLayer1;

	public Player(int x, int y, int vitesse, int reach, Model m, int vie, int damage, boolean isPlayer1) {
		super(x, y, vitesse, reach, m, vie, damage);
		this.isPLayer1=isPlayer1;
	}

}
