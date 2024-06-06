package model;

public class Model {
    Entity[][] grid;
    int size;

    public Model(int size) {
        grid = new Entity[size][size];
        this.size = size;
        
        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                grid[i][j] = new Vide(i,j,this);
            }
        }
        new Snake(0,0,this);
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
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(grid[j][i].toString());
                }
            }
            System.out.println();
        }
        System.out.println("\n\n");   
    }
    
    public void set(int x, int y, Entity t) {
        grid[x][y]=t;
    }

    public int getSize() {
        return size;
    }
}