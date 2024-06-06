package model;

import ai.*;

public abstract class Entity {
    FSM fsm;
    State current;
    int x,y;
    Model model;
    int direction;

    public Entity(int x, int y, Model m) {
        this.fsm = new FSM();
        this.model=m;
        this.x=x;
        this.y=y;
        direction=Direction.W;
    }

    public boolean eval_cell(int dir, int category) {
    	Entity e=null;;   
        switch (dir) {
            case Direction.N:
                e=model.getEntity(x,y-1);
                break;
            case Direction.S:
                e=model.getEntity(x,y+1);
                break;
            case Direction.E:
                e=model.getEntity(x+1,y);
                break;
            case Direction.W:
                e=model.getEntity(x-1,y);
                break;
            case Direction.R:
                switch (direction) {
                    case Direction.N:
                        e=model.getEntity(x+1,y);
                        break;
                    case Direction.S:
                        e=model.getEntity(x-1,y);
                        break;
                    case Direction.E:
                        e=model.getEntity(x,y+1);
                        break;
                    case Direction.W:
                        e=model.getEntity(x,y-1);
                        break;
                    default:
                        break;
                }
                break;
            case Direction.L:
                switch (direction) {
                    case Direction.N:
                        e=model.getEntity(x-1,y);
                        break;
                    case Direction.S:
                        e=model.getEntity(x+1,y);
                        break;
                    case Direction.E:
                        e=model.getEntity(x,y-1);
                        break;
                    case Direction.W:
                        e=model.getEntity(x,y+1);
                        break;
                    default:
                        break;
                }
                break;
            case Direction.F:
                switch (direction) {
                    case Direction.N:
                        e=model.getEntity(x,y-1);
                        break;
                    case Direction.S:
                        e=model.getEntity(x,y+1);
                        break;
                    case Direction.E:
                        e=model.getEntity(x+1,y);
                        break;
                    case Direction.W:
                        e=model.getEntity(x-1,y);
                        break;
                    default:
                        break;
                }
                break;
            case Direction.B:
                switch (direction) {
                    case Direction.N:
                        e=model.getEntity(x,y+1);
                        break;
                    case Direction.S:
                        e=model.getEntity(x,y-1);
                        break;
                    case Direction.E:
                        e=model.getEntity(x-1,y);
                        break;
                    case Direction.W:
                        e=model.getEntity(x+1,y);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        switch (category) {
            case Category.T:
            case Category.A:
                if (e instanceof Snake) {
                    return true;
                }
                return false;
            /*case Category.P :
                if (e instanceof Apple) {
                    return true;
                }
                return false;*/
            case Category.V :
                if (e instanceof Vide) {
                    return true;
                }
                return false;
            case Category.ME:
                
            default:
                break;
        }
        return true;
    }

    public FSM getFSM() {
        return fsm;
    }

    public State getState() {
        return current;
    }
    public void setState(State s) {
        this.current=s;
    }

public abstract void do_move(int direction);
public abstract void do_pick();
public abstract void do_egg();

public abstract void do_turn(int direction);


}
