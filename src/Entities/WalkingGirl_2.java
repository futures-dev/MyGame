package Entities;

import java.util.LinkedList;

/**
 * Created by Андрей on 21.09.2015.
 */
public class WalkingGirl_2 extends Room implements MoveableEntity {

    protected RealEntity original;


    private int count = 1;
    private int health = 300;

    public WalkingGirl_2(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg, LinkedList<Item> keysNeeded, LinkedList<Item> items, String illegalAccessMsg) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessMsg);
        level.CheckIn(this);
        original = new FireRoom(level.getID(), "Room", level, '*', posRow, posCol,
                "This room is on fire! Get away!", null, null, 10, "");
    }

    @Override
    public void Update() throws InterruptedException {
        if (!(level.character.row == row && level.character.col == col)) {
            if (count++ % 4 < 2)
                level.changePosition(this, row, col + 1);
            else
                level.changePosition(this, row, col - 1);
            health -= 10;
            if (health <= 0)
                throw new InterruptedException("The girl has burnt! Try again.");
        } else {
            level.changePosition(this, 4, 8);
        }
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
