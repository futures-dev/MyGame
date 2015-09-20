package Entities;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
class WalkingGirl_1 extends TestRoom implements MoveableEntity{

    public WalkingGirl_1(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                         LinkedList<Item> keysNeeded, LinkedList<Item> items, String[] options,
                         LinkedList<Integer> correct, String wrong, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, options, correct, wrong, illegalAccessString);
        level.CheckIn(this);
        original = new Room(level.getID(),"Room",level,'O',posRow,posCol,
                "You are in an old and wet cage with several doors.", null,null,"");
    }

    protected RealEntity original;

    @Override
    public void enter() throws IllegalAccessException,InterruptedException{
        super.enter();
        for (String s : getOptions())
            System.out.println(s);
        System.out.println("Input action number: ");
        Scanner scanner = new Scanner(System.in);
        boolean ok = true;
        do {
            String s = scanner.nextLine();
            int t = -1;
            try{
                t = Integer.parseInt(s.substring(0, 1));
            }
            catch (Exception e){
                ok=false;
            }
            if ((t>0&&t<=getOptions().length)){
                if (!getCorrect().contains(t)){
                    System.out.println(getWrong());
                    level.getCharacter().setHealth(0);}
            }
            else
                ok=false;
        }
        while (!ok);
    }


    private int count=1;
    @Override
    public void Update(){
        //if (!(level.character.row==row&&level.character.col==col)){
        if (count++%6<3)
            level.changePosition(this,row,col+1);
        else
            level.changePosition(this,row,col-1);
        //}
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
