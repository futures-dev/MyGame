package Entities;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
class TestRoom extends Room{
    private String[] options;
    private LinkedList<Integer> correct;
    private String wrong;

    public TestRoom(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                    LinkedList<Item> keysNeeded, LinkedList<Item> items, String[] options,
                    LinkedList<Integer> correct, String wrong, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessString);
        this.options = options;
        this.correct = correct;
        this.wrong = wrong;
    }

    @Override
    public void enter() throws IllegalAccessException,InterruptedException{
        super.enter();
    }

    public String[] getOptions() {
        return options;
    }

    public LinkedList<Integer> getCorrect() {
        return correct;
    }

    public String getWrong() {
        return wrong;
    }
}
