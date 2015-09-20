package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
public abstract class Entity implements Updatable {
    private int ID;
    protected static String className;
    private String name;
    protected Level level;

    public Entity(int ID, String name, Level level) {
        this.ID = ID;
        this.name = name;
        this.level = level;
        if (level!=null)
            level.CheckIn(this);
    }

    public static String getClassName() {
        return className;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
    public void Update(){}

    public Level getLevel() {
        return level;
    }
}
