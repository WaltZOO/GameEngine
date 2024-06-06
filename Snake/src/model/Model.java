package model;

public class Model {
    Entity[][] grid;

    Model(int taille) {
        grid = new Entity[taille][taille];
        grid[0][0] = new Snake();
    }

    void update() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].getFSM().step(grid[i][j]);
                }
            }
        }
    }
    public Entity getEntity(int x, int y) {
        if (x>0 && x<grid.length && y>0 && y<grid.length) {
            return grid[x] [y];
        }
        return null;
    }
    void display() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(grid[i][j].toString());
                }
            }
            System.out.println();
        }
    }

}