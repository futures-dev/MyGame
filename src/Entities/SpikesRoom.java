package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
class SpikesRoom extends Room {
    private int dmg;

    public SpikesRoom(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                      LinkedList<Item> keysNeeded, LinkedList<Item> items,int dmg, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items,illegalAccessString);
        this.dmg=dmg;
    }

    @Override
    public void enter() throws IllegalAccessException,InterruptedException{
        super.enter();
        level.getCharacter().setHealth(level.getCharacter().getHealth() - dmg);
    }

}
