package ai;

import model.Entity;

public class Conjonction implements Condition {
	 public Conjonction(Condition c1, Condition c2){
	        this.cond1 = c1;
	        this.cond2 = c2;
	    }

	    Condition cond1, cond2;
	    
	    public boolean eval(Entity e){
	        return cond1.eval(e) && cond2.eval(e);
	    }

}
