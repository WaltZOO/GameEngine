package model;

public class TimerCondition implements WinCondition {
    private String msg;
    private long timer;

    public TimerCondition(String msg, int timer) {
        this.msg = msg;
        this.timer = timer;
    }

    public long getTimer() {
        return timer;
    }

    @Override
    public String getMsg() {
    	return msg;
    }
    
	@Override
	public boolean evalCond(long elapsed) {
		if (elapsed<1000)
			timer -= elapsed;
		if(timer <= 0) {
			return true;
		}
		return false;
	}
}