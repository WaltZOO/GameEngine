package ai;

import model.Entity;

class Egg implements Action {

    String direction;
    String category;

    Egg() {
    }

    // Meme signature pour direction ou category
    public Egg(String direction_category, boolean is_dir) {
        if (is_dir)
            this.direction = direction_category;
        else
            this.category = direction_category;
    }

    public Egg(String direction, String category) {
        this.direction = direction;
        this.category = category;
    }

    @Override
    public void exec(Entity e) {
        e.do_egg(direction, category);
    }
}
