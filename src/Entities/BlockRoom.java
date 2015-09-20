package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
class BlockRoom extends Room {
    public BlockRoom(int ID, String name, Level level, char symbol, int posRow, int posCol) {
        super(ID, name, level, symbol, posRow, posCol, "This should not be written.",null,null,"");
    }

    @Override
    public void enter()throws IllegalAccessException,InterruptedException{
        throw new IllegalAccessException("There's no door there.");
    }
}
