package Entities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
public class Level implements Updater{
    private RealEntity[][] entityMap;
    private Engine.Map map = new Engine.Map();
    private Character character;
    private LinkedList<Updatable> updatables;
    private int currentID;
    private int getID(){
        currentID+=1;
        return currentID;
    }

    public Level(Character character, int ID) {
        currentID=0;
        map = new Engine.Map();
        this.character = character;
        updatables = new LinkedList<>();
        entityMap = new RealEntity[map.getRowN()][];
        for (int i = 0;i<entityMap.length;i++)
            entityMap[i] = new RealEntity[map.getColN()];
        try{
        switch (ID){
            case 1: load1();break;
            default: load1();break;
        }}
        catch(Exception e){
            System.out.println("Warning! Level could not initialize!");
            System.out.println(e.getMessage());
        }
        loop();
    }

    public RealEntity[][] getEntityMap() {
        return entityMap;
    }


    private void Update(){
        for (int i = 0;i<RowN;i++)
            for (int j = 0;j<ColN;j++)
                map.getMap()[i][j] = entityMap[i][j].getSymbol();
        map.getMap()[character.getRow()][character.getCol()] = character.getSymbol();
        map.Draw();
    }

    public void CheckIn(Updatable u){
        updatables.add(u);
    }
    public void Toggle(){
        for (Updatable u : updatables)
            u.Update();
        Update();
    }


    private int RowN;
    private int ColN;
    private void load1() throws Exception{
        // This level is for 10x10 map
        RowN = 10;
        ColN = 10;
        if (map.getColN()<10)
            throw new Exception("Too few columns in cmd for this level");
        if (map.getRowN()<10)
            throw new Exception("Too few rows in cmd for this level");
        character.setRow(1);
        character.setCol(1);
        character.setHealth(100);
        character.setItemBag(new LinkedList<Item>());
        for (int i = 0;i<RowN;i++)
            for (int j = 0;j<ColN;j++)
                entityMap[i][j] = new Room(getID(),"Room",this,'O',i,j,
                        "You are in an old and wet cage with several doors.", null,null,"");
        entityMap[8][5] = new Exit(getID(),"Exit",this,'@',8,5,"You've reached the exit from this level!", null,null,"");
        entityMap[4][4] = new TestRoom(getID(),"TestRoom",this,'%',4,4,"A beautiful girl is standing by a wall.",
                null,null,new String[]{"(1) Pass by.","(2) Tell her the weather is great."},
                new LinkedList<Integer>(Arrays.asList(1)),"She hates talks about weather and so killed you.","");
        entityMap[3][0] = new FireRoom(getID(),"FireRoom",this,'*',3,0,"Room on fire. You take "+20+"damage",null,
                new LinkedList<Item>(Arrays.asList(new Item(getID(),"Lighter",this))),20,"");
        entityMap[8][4] = new BlockRoom(getID(),"BlockRoom",this,'#',8,4);
        entityMap[7][4] = new BlockRoom(getID(),"BlockRoom",this,'#',7,4);
        entityMap[7][5] = new BlockRoom(getID(),"BlockRoom",this,'#',7,5);
        entityMap[8][6] = new Room(getID(),"",this,'Q',8,6,
                "It's absolutely dark here.", new LinkedList<Item>(Arrays.asList(new Item(-1,"Lighter",this))),null,
                "It's too dark there. You need a lighter.");
    }

    private void loop(){
        try{
        entityMap[character.getRow()][character.getCol()].enter();}
        catch (IllegalAccessException e){
            System.out.println("Invalid Level.");
        }
        catch(InterruptedException e){
            InterruptedExceptionHandler(e);
        }
        do
            next();
        while (!entityMap[character.getRow()][character.getCol()].getName().equals("Exit"));
    }

    private void InterruptedExceptionHandler(InterruptedException e) {
        System.out.println(e.getMessage());
        try {
            load1();
        }
        catch(Exception q){
            System.out.println("Invalid Level.");
        }
    }

    private void next() {
        Toggle();
        boolean ok = true;
        do {
            ok=true;
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toUpperCase();
            switch (input.charAt(0)) {
                case 'D': {
                    if (character.getCol() >= ColN - 1) {
                        ok = false;
                        break;
                    } else {
                        character.setCol(character.getCol() + 1);
                        try{
                            entityMap[character.getRow()][character.getCol()].enter();
                        }
                        catch(IllegalAccessException e){
                            System.out.println(e.getMessage());
                            ok=false;
                            character.setCol(character.getCol() - 1);
                        }
                        catch(InterruptedException e){
                            InterruptedExceptionHandler(e);
                        }
                    }
                    break;
                }
                case 'A': {
                    if (character.getCol() <= 0 ) {
                        ok = false;
                        break;
                    } else {
                        character.setCol(character.getCol() - 1);
                        try{
                            entityMap[character.getRow()][character.getCol()].enter();
                        }
                        catch(IllegalAccessException e){
                            System.out.println(e.getMessage());
                            ok=false;
                            character.setCol(character.getCol() + 1);
                        }
                        catch(InterruptedException e){
                            InterruptedExceptionHandler(e);
                        }
                    }
                    break;
                }
                case 'S': {
                    if (character.getRow() >= RowN - 1) {
                        ok = false;
                        break;
                    } else {
                        character.setRow(character.getRow() + 1);
                        try{
                            entityMap[character.getRow()][character.getCol()].enter();
                        }
                        catch(IllegalAccessException e){
                            System.out.println(e.getMessage());
                            ok=false;
                            character.setRow(character.getRow() - 1);
                        }
                        catch(InterruptedException e){
                            InterruptedExceptionHandler(e);
                        }
                    }
                    break;
                }
                case 'W': {
                    if (character.getRow() <= 0) {
                        ok = false;
                        break;
                    } else {
                        character.setRow(character.getRow() - 1);
                        try{
                            entityMap[character.getRow()][character.getCol()].enter();
                        }
                        catch(IllegalAccessException e){
                            System.out.println(e.getMessage());
                            ok=false;
                            character.setRow(character.getRow() + 1);
                        }
                        catch(InterruptedException e){
                            InterruptedExceptionHandler(e);
                        }
                    }
                    break;
                }
                case 'B': {
                    if (character.getItemBag().isEmpty())
                        System.out.println("Your bag is empty.");
                    else {
                        System.out.println("Your bag:");
                        boolean notfirst = false;
                        for (Item item : character.getItemBag())
                        {
                            System.out.print((notfirst?", ":"") + item.getName());
                            notfirst=true;
                        }
                        System.out.println();
                    }
                    System.out.println("Your health is: "+character.getHealth());

                    ok=false;
                    break;
                }
            }
        }
        while (!ok);
    }

    public Character getCharacter() {
        return character;
    }
}
