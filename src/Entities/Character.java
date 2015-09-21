package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
public class Character extends RealEntity {
    private int health;
    private LinkedList<Item> itemBag;

    public Character(String name) {
        super(0, name, null, '!', 0, 0);
        level = new Level_1(this);
        level = new Level_2(this);
        level = new Level_3(this);
        level = new Level_4(this);
    }

    protected int getHealth() {
        return health;
    }

    protected void setHealth(int health) throws InterruptedException {
        if (health <= 0)
            throw new InterruptedException("You are dead :( Try again!");
        else
            this.health = health;
    }

    protected LinkedList<Item> getItemBag() {
        return itemBag;
    }

    protected void setItemBag(LinkedList<Item> itemBag) {
        this.itemBag = itemBag;
    }
}
