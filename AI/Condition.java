public interface Condition {

    boolean eval(Entity e){
        return e.do_eval();
    }

}