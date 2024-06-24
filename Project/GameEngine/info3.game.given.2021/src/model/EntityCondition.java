package model;

import java.util.ArrayList;
import java.util.List;

public class EntityCondition implements WinCondition {
    String msg;
    World w;
    ArrayList<String> entitiesPresent;
    boolean present; // specify if we want the entities to be present of the world

    EntityCondition(String msg, World w, ArrayList<String> entitiesPresent, boolean present) {
        this.msg = msg;
        this.w = w;
        this.entitiesPresent = entitiesPresent;
        this.present = present;
    }
    
    public String getMsg() {
    	return msg;
    }
    
    public World getWorld() {
    	return w;    	
    }
    
    @Override
    public boolean evalCond(long elapsed) {
        if (present)
            return evalPresent();
        else
            return evalAbsent();
    }

    boolean evalPresent() {
    	List<Entity> inZeBoite =  w.qt.updateEntities();
        for (String toCheck : entitiesPresent) {
        	boolean tmp = false;
            for (Entity inTheWorld : inZeBoite) {
                if (toCheck.equals(inTheWorld.name)) // if we find the entity
                    tmp = true;;
            }
            if(tmp == false) // if we didn't find the entity
            	return false;
        }
        return true; 
    }

    boolean evalAbsent() {
    	List<Entity> inZeBoite =  w.qt.updateEntities();
        for (String toCheck : entitiesPresent) {
            for (Entity inTheWorld : inZeBoite) {
                if (toCheck.equals(inTheWorld.name)) // if we find the entity
                    return false;
            }
        }
        return true; 
    }
}