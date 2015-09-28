package Entities;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by Computer on 20.09.2015.
 */
class Room extends RealEntity {
    private String onEnterMsg;
    private String illegalAccessMsg;
    private LinkedList<Item> keysNeeded;
    private LinkedList<Item> items;

    public Room(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg,
                LinkedList<Item> keysNeeded, LinkedList<Item> items, String illegalAccessMsg) {
        super(ID, name, level, symbol, posRow, posCol);
        this.onEnterMsg = onEnterMsg;
        this.keysNeeded = keysNeeded;
        this.items = items;
        this.illegalAccessMsg = illegalAccessMsg;
    }

    @Override
    public void enter() throws IllegalAccessException, InterruptedException {
        if (keysNeeded != null) {
            boolean ok = true;
            //String t = "";
            for (Item key : keysNeeded) {
                boolean found = false;
                for (Item item : level.getCharacter().getItemBag()) {
                    if (Objects.equals(item.getName(), key.getName()))
                        found = true;
                }
                if (!found) {
                    ok = false;
                    //t = key.getName();
                    break;
                }
            }
            if (!ok)
                throw new IllegalAccessException(illegalAccessMsg);
        }
        if (items != null) {
            for (Item item : items) {
                boolean found = false;
                for (Item bagItem : level.getCharacter().getItemBag()) {
                    if (Objects.equals(bagItem.getName(), item.getName()))
                        found = true;
                }
                if (!found)
                    level.getCharacter().getItemBag().add(item);
            }
        }
        System.out.println(onEnterMsg);
    }
}
