package model;

import java.util.ArrayList;

public class Victory {

	String msg;
    ArrayList<WinCondition> conditions;
    ArrayList<String> ops;
    long elapsed;

    Victory(String msg, ArrayList<WinCondition> conditions, ArrayList<String> ops) {
        this.conditions = conditions;
        this.ops = ops;
        this.msg = msg;
    }

	public ArrayList<WinCondition> getWinConditions() {
		return this.conditions;
	}
    
    public String VictoryMsg() {
    	if (this.msg != null) {
    		return msg;
    	}
    	else {
    		for(WinCondition wc: conditions) {
    			if(wc.evalCond(elapsed))
    				return wc.getMsg();
    		}
    	}
    	return " ";
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

	public TimerCondition getTimer() {
		for (WinCondition w : this.conditions) {
			if (w instanceof TimerCondition)
				return (TimerCondition) w;
		}
		return null;
	}
}