package Entities;

import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
class TestRoom extends Room {
    private String[] options;
    private LinkedList<Integer> correct;
    private String wrong;

    TestRoom(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
             LinkedList<Item> keysNeeded, LinkedList<Item> items, String[] options,
             LinkedList<Integer> correct, String wrong, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessString);
        this.options = options;
        this.correct = correct;
        this.wrong = wrong;
    }

    @Override
    public void enter() throws IllegalAccessException, InterruptedException {
        super.enter();
    }

    String[] getOptions() {
        return options;
    }

    LinkedList<Integer> getCorrect() {
        return correct;
    }

    String getWrong() {
        return wrong;
    }
}
