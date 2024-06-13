package ai;

import model.Entity;
import ai.Condition;

public interface Condition {
    public boolean eval(Entity e);
}