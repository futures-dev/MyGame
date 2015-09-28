package Entities;

/**
 * Created by Computer on 20.09.2015.
 */
abstract class Entity implements Updatable {
    Level level;
    private int ID;
    private String name;

    Entity(int ID, String name, Level level) {
        this.ID = ID;
        this.name = name;
        this.level = level;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void Update() throws InterruptedException {
    }

    public Level getLevel() {
        return level;
    }
}
