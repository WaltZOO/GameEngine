public class Model {
    Entity[][] grid;

    Model(int taille) {
        grid = new Entity[taille][taille];
        grid[0][0] = new Snake();

    }

    void display() {
    }

}