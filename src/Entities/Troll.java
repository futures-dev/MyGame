package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
public class Troll extends Room implements MoveableEntity{

    protected RealEntity original;

    public Troll(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                    LinkedList<Item> keysNeeded, LinkedList<Item> items,int dmg, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items,illegalAccessString);
        level.CheckIn(this);
        original = new Room(level.getID(),"Room",level,'O',posRow,posCol,
                "You are in an old and wet cage with several doors.", null,null,"");
    }

    @Override
    public void enter() throws IllegalAccessException,InterruptedException{
        super.enter();
        level.getCharacter().setHealth(0);
    }

    private int count=1;
    @Override
    public void Update(){
        if (count++%4<2)
            level.changePosition(this,row,col+1);
        else
            level.changePosition(this,row,col-1);
    }

    public RealEntity getRealEntity(){
        return this;
    }

    public void setOriginal(RealEntity original){
        this.original = original;
    }

    public RealEntity getOriginal(){
        return original;
    }
}
