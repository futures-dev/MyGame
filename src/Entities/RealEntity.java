package Entities;

/**
 * Created by Computer on 20.09.2015.
 */
public abstract class RealEntity extends Entity {
    private char symbol;
    private int Row;
    private int Col;

    public RealEntity(int ID, String name, Level level, char symbol, int Row, int Col) {
        super(ID, name, level);
        this.symbol = symbol;
        this.Row = Row;
        this.Col = Col;
    }


    public char getSymbol() {
        return symbol;
    }

    public int getCol() {
        return Col;
    }

    public void setCol(int col) {
        this.Col = col;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        this.Row = row;
    }

    public void enter() throws IllegalAccessException,InterruptedException{}
}
