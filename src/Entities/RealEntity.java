package Entities;

/**
 * Created by Computer on 20.09.2015.
 */
abstract class RealEntity extends Entity {
    int row;
    int col;
    private char symbol;

    RealEntity(int ID, String name, Level level, char symbol, int Row, int Col) {
        super(ID, name, level);
        this.symbol = symbol;
        this.row = Row;
        this.col = Col;
    }


    public char getSymbol() {
        return symbol;
    }

    public void enter() throws IllegalAccessException, InterruptedException {
    }
}
