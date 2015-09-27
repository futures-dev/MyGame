package Entities;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Computer on 20.09.2015.
 */
class Level_1 extends Level {

    public Level_1(Character character) {
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
        entityMap[8][5] = new QuestExit(getID(), "Exit", this, '@', 8, 5, "You've reached the exit from this level!", null, null, "");
        entityMap[4][4] = new WalkingGirl_1(getID(), "TestRoom", this, '%', 4, 4, "A beautiful girl is standing by a wall.",
                null, null, new String[]{"(1) Pass by.", "(2) Tell her the weather is great."},
                new LinkedList<Integer>(Arrays.asList(1)), "She hates talks about weather and so killed you.", "");
        entityMap[3][0] = new FireRoom(getID(), "FireRoom", this, '*', 3, 0, "Room on fire. You take " + 20 + "damage", null,
                new LinkedList<Item>(Arrays.asList(new Item(getID(), "Lighter", this))), 20, "");
        entityMap[8][4] = new BlockRoom(getID(), "BlockRoom", this, '#', 8, 4);
        entityMap[7][4] = new BlockRoom(getID(), "BlockRoom", this, '#', 7, 4);
        entityMap[7][5] = new BlockRoom(getID(), "BlockRoom", this, '#', 7, 5);
        entityMap[8][6] = new Room(getID(), "", this, 'Q', 8, 6,
                "It's absolutely dark here.", new LinkedList<Item>(Arrays.asList(new Item(-1, "Lighter", this))), null,
                "It's too dark there. You need a lighter.");

        System.out.println();
        System.out.println("Level_1. Tutorial.");
        System.out.println("You will find a map underneath. Here is some of the legend for the symbols:");
        System.out.println("O: Usual room. You can find items in some rooms.");
        System.out.println("!: This is where you are now.");
        System.out.println("@: This is the exit from the level.");
        System.out.println("*: Caution! This is a room full of fire. Fire will hurt your health.");
        System.out.println("|: Caution! This is a room with a trap. A trap can hurt or even kill you.");
        System.out.println("#: This is a block which you can't pass through.");
        System.out.println("Q: To enter these rooms you will need special items.");
        System.out.println("%: You will find something unusual in these rooms.");
        System.out.println("Other levels may introduce other legend. For now, let's learn to interact with the map:");
        System.out.println("<input>d<enter>: Attempt to move to the room to the right.");
        System.out.println("<input>a<enter>: Attempt to move to the room to the left.");
        System.out.println("<input>w<enter>: Attempt to move to the room to the up.");
        System.out.println("<input>s<enter>: Attempt to move to the room to the down.");
        System.out.println("<input>b<enter>: Watch your belongings and health.");
        System.out.println();
    }
}
