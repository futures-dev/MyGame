package Entities;

import java.util.LinkedList;

/**
 * Created by Андрей on 21.09.2015.
 */
public class Level_3 extends Level {
    public Level_3(Character character) {
        super(character);
    }

    @Override
    protected void load() throws ExceptionInInitializerError {
        // This level is for 10x10 map
        RowN = 10;
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
                entityMap[i][j] = new Room(getID(), "Room", this, 'O', i, j,
                        "You are in an old and wet cage with several doors.", null, null, "");
        for (int j = 2; j < 9; j++)
            entityMap[2][j] = new SpikesRoom(getID(), "Trap", this, '|', 2, j, "You got into a trap.", null, null, 100, "");
        for (int j = 2; j < 4; j++)
            entityMap[8][j] = new SpikesRoom(getID(), "Trap", this, '|', 8, j, "You got into a trap.", null, null, 100, "");
        for (int j = 5; j < 9; j++)
            entityMap[8][j] = new SpikesRoom(getID(), "Trap", this, '|', 8, j, "You got into a trap.", null, null, 100, "");
        for (int i = 3; i < 8; i++)
            entityMap[i][2] = new SpikesRoom(getID(), "Trap", this, '|', i, 2, "You got into a trap.", null, null, 100, "");
        for (int i = 3; i < 8; i++)
            entityMap[i][8] = new SpikesRoom(getID(), "Trap", this, '|', i, 8, "You got into a trap.", null, null, 100, "");
        entityMap[5][4] = new BlockRoom(getID(), "BlockRoom", this, '#', 5, 4);
        entityMap[6][4] = new BlockRoom(getID(), "BlockRoom", this, '#', 6, 4);
        entityMap[6][5] = new BlockRoom(getID(), "BlockRoom", this, '#', 6, 5);
        entityMap[6][6] = new BlockRoom(getID(), "BlockRoom", this, '#', 6, 6);
        entityMap[5][6] = new BlockRoom(getID(), "BlockRoom", this, '#', 5, 6);
        entityMap[5][5] = new Exit(getID(), "Exit", this, '@', 5, 5, "You've reached the exit from this level!", null, null, "");
        entityMap[4][5] = new Troll(getID(), "Troll", this, 'Z', 4, 5, "A troll wants to eat you.", null, null, 100, "");

        System.out.println();
        System.out.println("Level_3. Troll.");
        System.out.println("The exit is guarded by a troll. Be careful.");
        System.out.println();
    }
}
