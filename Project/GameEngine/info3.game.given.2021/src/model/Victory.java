package model;

import java.util.ArrayList;

public class Victory {

    ArrayList<WinCondition> conditions;
    ArrayList<String> ops;
    long elapsed;

    Victory(ArrayList<WinCondition> conditions, ArrayList<String> ops, TimerCondition timerCond) {
        this.conditions = conditions;
        this.ops = ops;
    }

    public ArrayList<WinCondition> getWinConditions(){
    	return this.conditions;
    }
    
    boolean evalCond(long elapsed) {
        boolean res = conditions.get(0).evalCond(elapsed);
        for (int i = 0; i < ops.size(); i++) {
            switch (ops.get(i)) {
                case "&&":
                	
                    res = res && conditions.get(i + 1).evalCond(elapsed);
                    break;
                case "||":
                    res = res || conditions.get(i + 1).evalCond(elapsed);
                    break;
                default:
                    return false;
            }
        }
        return res;
    }
}