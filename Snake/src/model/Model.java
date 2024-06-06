package model;

import java.util.ArrayList;

public class Model {
    ArrayList<Entity> grid;
    int size;

    public Model(int size) {
        grid = new ArrayList<Entity>();
        this.size = size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.add(new Vide(i, j, this));
            }
        }
        new Snake(0, 0, this);
    }

    public void update() {

        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).getFSM().step(grid.get(i));
        }
    }

    public Entity getEntity(int x, int y) {
        if(x<0 || x>=size || y<0 || y>=size) {
            return null;
        }
        for(int i = 0; i<grid.size(); i++) {
        	Entity tmp = grid.get(i);
        	if(tmp.x == x && tmp.y == y ) {
        		return tmp;
        	}
        }
        return null;
    }

    public void display() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(getEntity(i,j).toString());
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    public int getSize() {
        return size;
    }
}