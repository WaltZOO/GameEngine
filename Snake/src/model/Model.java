package model;

public class Model {
    Entity[][] grid;

    public Model(int taille) {
        grid = new Entity[taille][taille];

        SnakeHead snakeHead = new SnakeHead(0, 0, this);
        grid[0][0] = new Snake(snakeHead,0,0,this);
    }

    public void update() {
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

    public void display() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(grid[i][j].toString());
                }
            }
            System.out.println();
        }           
    }
    public void set(int x, int y, Entity t) {
        grid[x][y]=t;
    }

}