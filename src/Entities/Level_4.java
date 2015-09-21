package Entities;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Андрей on 21.09.2015.
 */
public class Level_4 extends Level {
    public Level_4(Character character) {
        super(character);
    }

    @Override
    protected void load() throws ExceptionInInitializerError {
        // This level is for 11x10 map
        RowN = 11;
        ColN = 10;
        if (map.getColN() < 10)
            throw new ExceptionInInitializerError("Too few columns in cmd for this level");
        if (map.getRowN() < 10)
            throw new ExceptionInInitializerError("Too few rows in cmd for this level");
        character.row = 1;
        character.col = 1;
        try {
            character.setHealth(100);
        } catch (InterruptedException e) {
            throw new ExceptionInInitializerError("Character setHealth fails.");
        }
        character.setItemBag(new LinkedList<Item>());
        updatables = new LinkedList<>();
        for (int i = 0; i < RowN; i++)
            for (int j = 0; j < ColN; j++)
                entityMap[i][j] = new BlockRoom(getID(), "BlockRoom", this, '#', i, j);
        entityMap[1][1] = new Room(getID(), "Room", this, 'O', 1, 1,
                "You feel so lonely in this labyrinth", null, null, "");
        entityMap[2][1] = new Room(getID(), "Room", this, 'O', 2, 1,
                "You feel so lonely in this labyrinth", null, null, "");

        for (int j = 0; j < ColN; j++)
            for (int i = 3; i < 5; i++)
                entityMap[i][j] = new Room(getID(), "Room", this, 'O', i, j, "You feel so lonely in this labyrinth", null, null, "");
        for (int j = 7; j < ColN; j++)
            for (int i = 5; i < 7; i++)
                entityMap[i][j] = new Room(getID(), "Room", this, 'O', i, j, "You feel so lonely in this labyrinth", null, null, "");
        entityMap[7][7] = new Room(getID(), "Room", this, 'O', 7, 9, "You feel so lonely in this labyrinth", null, null, "");
        entityMap[7][9] = new Room(getID(), "Room", this, 'O', 7, 9, "You feel so lonely in this labyrinth", null, null, "");
        entityMap[7][6] = new Room(getID(), "Room", this, 'O', 7, 6, "You feel so lonely in this labyrinth", null, null, "");
        for (int j = 4; j < 6; j++)
            for (int i = 7; i < 10; i++)
                entityMap[i][j] = new Room(getID(), "Room", this, 'O', i, j, "You feel so lonely in this labyrinth", null, null, "");
        entityMap[10][3] = new Room(getID(), "Room", this, 'O', 10, 3, "You feel so lonely in this labyrinth", null, null, "");
        entityMap[10][5] = new Room(getID(), "Room", this, 'O', 10, 5, "You feel so lonely in this labyrinth", null, null, "");
        for (int j = 7; j < ColN; j++)
            entityMap[10][j] = new Room(getID(), "Room", this, 'O', 10, j, "You feel so lonely in this labyrinth", null, null, "");

        entityMap[3][4] = new SpikesRoom(getID(), "Trap", this, '|', 3, 4, "You got into a trap.", null, null, 50, "");
        entityMap[4][6] = new SpikesRoom(getID(), "Trap", this, '|', 4, 6, "You got into a trap.", null, null, 50, "");
        entityMap[7][8] = new SpikesRoom(getID(), "Trap", this, '|', 7, 8, "You got into a trap.", null, null, 50, "");


        entityMap[10][4] = new Room(getID(), "Room", this, 'Q', 10, 4,
                "You've opened this door with a key", new LinkedList<Item>(Arrays.asList(new Item(-1, "Key", this))),
                null, "The door is locked. You need a key.");
        entityMap[10][6] = new Room(getID(), "Room", this, 'Q', 10, 6,
                "You found a key!", null, new LinkedList<Item>(Arrays.asList(new Item(getID(), "Key", this))), "");
        entityMap[10][2] = new Exit(getID(), "Exit", this, '@', 10, 2, "You've reached the exit from this level!",
                null, null, "");
        System.out.println();
        System.out.println("Level_4. Fog.");
        System.out.println("This labyrinth is covered with fog, so you can't see what's around you.");
        System.out.println("Try to reach the exit without getting trapped");
        System.out.println();
    }

    @Override
    protected void Update() {
        for (int i = 0; i < RowN; i++)
            for (int j = 0; j < ColN; j++)
                map.getMap()[i][j] = '?';
        map.getMap()[character.row][character.col] = character.getSymbol();
        map.Draw();
    }
}
