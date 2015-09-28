package Engine;

/**
 * Created by Computer on 20.09.2015.
 */
public class Map {
    private int rowN = 12;
    private int colN = 12;
    private char[][] map;

    public Map() {
        map = new char[rowN][];
        for (int i = 0; i < rowN; i++)
            map[i] = new char[colN];
    }

    public void Draw() {
        System.out.println();
        for (int i = 0; i < getRowN(); i++) {
            System.out.println(String.valueOf(getMap()[i]));
        }
    }

    public int getRowN() {
        return rowN;
    }

    public int getColN() {
        return colN;
    }

    public char[][] getMap() {
        return map;
    }

}
