package Entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Андрей on 21.09.2015.
 */
class Level_2 extends Level {
    public Level_2(Character character) {
        super(character);
    }

    @Override
    public void load() {
        // This level is for 10x10 map
        RowN = 10;
        ColN = 10;
        if (map.getColN() < 10)
            throw new ExceptionInInitializerError("Too few columns in cmd for this level");
        if (map.getRowN() < 10)
            throw new ExceptionInInitializerError("Too few rows in cmd for this level");
        character.row = 1;
        character.col = 1;
        updatables = new LinkedList<>();
        try {
            character.setHealth(100);
        } catch (InterruptedException e) {
            throw new ExceptionInInitializerError("Character setHealth fails.");
        }
        character.setItemBag(new LinkedList<Item>());
        for (int i = 0; i < RowN; i++)
            for (int j = 0; j < ColN; j++)
                entityMap[i][j] = new Room(getID(), "Room", this, 'O', i, j,
                        "You are in one of the rooms of the building.", null, null, "");
        for (int j = 2; j < 7; j++)
            entityMap[3][j] = new BlockRoom(getID(), "BlockRoom", this, '#', 3, j);
        for (int j = 2; j < 7; j++)
            entityMap[5][j] = new BlockRoom(getID(), "BlockRoom", this, '#', 5, j);
        entityMap[4][2] = new BlockRoom(getID(), "BlockRoom", this, '#', 4, 2);
        entityMap[4][6] = new Room(getID(), "Room", this, 'Q', 4, 6,
                "You are in an old and wet cage with several doors.",
                new LinkedList<Item>(Arrays.asList(new Item(-1, "Key", this))), null, "The door is locked. You need a key.");
        entityMap[7][3] = new FireRoom(getID(), "FireRoom", this, '*', 7, 3,
                "This room is on fire! But there is something on the floor. It's a key!", null,
                new LinkedList<Item>(Arrays.asList(new Item(getID(), "Key", this))), 30, "");
        entityMap[4][3] = new FireRoom(getID(), "FireRoom", this, '*', 4, 3,
                "This room is on fire! Get away!", null, null, 10, "");
        entityMap[4][4] = new FireRoom(getID(), "FireRoom", this, '*', 4, 4,
                "This room is on fire! Get away!", null, null, 10, "");
        entityMap[4][5] = new FireRoom(getID(), "FireRoom", this, '*', 4, 5,
                "This room is on fire! Get away!", null, null, 10, "");
        entityMap[7][7] = new Exit(getID(), "Exit", this, '@', 7, 7, "You've reached the exit from this level!",
                new LinkedList<Item>(Arrays.asList(new Item(-1, "Girl's gratitude", this))), null,
                "You need to rescue the girl first");
        entityMap[4][4] = new WalkingGirl_2(getID(), "Girl", this, '&', 4, 4, "You've rescued me! Thank you!", null,
                new LinkedList<Item>(Arrays.asList(new Item(getID(), "Girl's gratitude", this))), "");

        System.out.println();
        System.out.println("Level_2. Rescue the girl.");
        System.out.println("You turned up in a building. Some of the rooms are on fire.");
        System.out.println("There is a girl blocked somewhere nearby. You must save her!");
        System.out.println();
    }
}
