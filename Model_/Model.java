import java.lang.Thread;

public class Model {
    Entity[][] grid;
    int id_snake;
    int size;
    static int TAILLESNAKE = 7;

    public Model(int size) {
        grid = new Entity[size][size];
        id_snake = 0;
        this.size = size;
        grid[0][0] = new Snake(id_snake++, TAILLESNAKE, true);
    }

    private void update() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                grid[i][j].getAutomate().step(grid[i][j]);
            }
        }
    }

    public void run() {
        while (true) {
            update();
            sleep(500);
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < grid.length; i++) {
            res += "|";
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    res += " ";
                } else {
                    res += grid[i][j].toString();
                }
                res += "|";
            }
            res += "\n";
        }
        return res;
    }

}