public class Snake extends Entity {
    int longueur;
    boolean head;

    public Snake(int longueur, boolean head) {
        this.longueur = longueur;
        this.head = head;
        this.aut = new Automate();
        aut.add_transition(new Transition())
    }

}