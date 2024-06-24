package model;

public class TimerCondition implements WinCondition {
    private String msg;
    private long timer;

    public TimerCondition(String msg, int timer) {
        this.msg = msg;
        this.timer = timer;
    }

    public String getMsg() {
        return this.msg;
    }

    public long getTimer() {
        return timer;
    }

    
	@Override
	public boolean evalCond(long elapsed) {
		timer -= elapsed;
		if(timer <= 0) {
			return true;
		}
		return false;
	}
}