public class Snake extends Entity {
    int longueur;
    boolean isHead;
    // Pour différencier les serpents
    int id;

    public Snake(int id,int longueur, boolean isHead) {
        this.id =id;
        this.longueur = longueur;
        this.isHead = isHead;
        this.aut = new Automate();
        
        aut.add_transition(new Transition(new ));
    }

    public void do_move() {
        // NYI
    }

    public void do_pick() {
        // NYI
    }

    public void do_egg() {
        // NYI
    }

}