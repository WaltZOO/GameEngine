package model;

public interface WinCondition {
	
	public boolean evalCond(long elapsed);
	
	public String getMsg();

}
