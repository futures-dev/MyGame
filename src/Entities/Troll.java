package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
public class Troll extends Room implements MoveableEntity {

    private RealEntity original;
    private int dmg;
    private int count = 2;

    public Troll(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                 LinkedList<Item> keysNeeded, LinkedList<Item> items, int dmg, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessString);
        level.CheckIn(this);
        original = new Room(level.getID(), "Room", level, 'O', posRow, posCol,
                "You are in an old and wet cage with several doors.", null, null, "");
        this.dmg = dmg;
    }

    @Override
    public void enter() throws IllegalAccessException, InterruptedException {
        super.enter();
        level.getCharacter().setHealth(level.getCharacter().getHealth() - dmg);
    }

    @Override
    public void Update() throws InterruptedException {
        if (count++ % 8 < 4)
            level.changePosition(this, row, col + 1);
        else
            level.changePosition(this, row, col - 1);
    }

    public RealEntity getRealEntity() {
        return this;
    }

    public RealEntity getOriginal() {
        return original;
    }

    public void setOriginal(RealEntity original) {
        this.original = original;
    }
}
