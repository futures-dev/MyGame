package Entities;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
public class TestRoom extends Room{
    private String[] options;
    private LinkedList<Integer> correct;
    private String wrong;

    public TestRoom(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                    LinkedList<Item> keysNeeded, LinkedList<Item> items, String[] options, LinkedList<Integer> correct,
                    String wrong, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessString);
        this.options = options;
        this.correct = correct;
        this.wrong = wrong;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public void enter() throws IllegalAccessException,InterruptedException{
        super.enter();
        for (String s : options)
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
            if ((t>0&&t<=options.length)){
                if (!correct.contains(t)){
                    System.out.println(wrong);
                    level.getCharacter().setHealth(0);}
            }
            else
                ok=false;
        }
        while (!ok);
    }
}
