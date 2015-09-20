package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
public class Character extends RealEntity {
    private int health;
    private LinkedList<Item> itemBag;

    public Character(String name) {
        super(0, name, null,'!', 0,0);
        level = new Level(this,1);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws InterruptedException {
        if (health<=0)
            throw new InterruptedException("You are dead :( Try again!");
        else
            this.health =health;
    }

    public LinkedList<Item> getItemBag() {
        return itemBag;
    }

    public void setItemBag(LinkedList<Item> itemBag) {
        this.itemBag = itemBag;
    }
}
