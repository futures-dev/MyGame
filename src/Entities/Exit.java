package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
public class Exit extends Room {

    public Exit(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                LinkedList<Item> keysNeeded, LinkedList<Item> items, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessString);
    }
}
