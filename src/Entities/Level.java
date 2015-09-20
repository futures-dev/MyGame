package Entities;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
abstract class Level implements Updater{
    protected RealEntity[][] entityMap;
    protected Engine.Map map;
    protected Character character;
    public Character getCharacter() {
        return character;
    }
    protected LinkedList<Updatable> updatables;
    protected int currentID;
    protected int getID(){
        currentID+=1;
        return currentID;
    }
    protected int RowN;
    protected int ColN;

    protected boolean completed = false;

    public boolean isCompleted(){
        return completed;
    }

    public Level(Character character) {
        currentID=0;
        map = new Engine.Map();
        this.character = character;
        updatables = new LinkedList<>();
        entityMap = new RealEntity[map.getRowN()][];
        for (int i = 0;i<entityMap.length;i++)
            entityMap[i] = new RealEntity[map.getColN()];
        try{
            load();
            loop();
        }
        catch(ExceptionInInitializerError e){
            System.out.println("Warning! Level could not initialize!");
            System.out.println(e.getMessage());
        }
    }

    protected void Update(){
        for (int i = 0;i< RowN;i++)
            for (int j = 0;j< ColN;j++)
                map.getMap()[i][j] = entityMap[i][j].getSymbol();
        map.getMap()[character.row][character.col] = character.getSymbol();
        map.Draw();
    }

    public void CheckIn(Updatable u){
        updatables.add(u);
    }
    public void Toggle(){
        for (Updatable u : updatables)
            u.Update();
        //Update();
    }



    protected abstract void load() throws ExceptionInInitializerError;

    protected void loop(){
        try{
        entityMap[character.row][character.col].enter();
        Update();}
        catch (IllegalAccessException e){
            System.out.println("Invalid Level.");
        }
        catch(InterruptedException e){
            InterruptedExceptionHandler(e);
        }
        do
            next();
        while (!entityMap[character.row][character.col].getName().equals("Exit"));
    }

    private void InterruptedExceptionHandler(InterruptedException e) {
        System.out.println(e.getMessage());
        try {
            load();
        }
        catch(ExceptionInInitializerError q){
            System.out.println("Warning! Level could not initialize!");
            System.out.println(e.getMessage());
        }
    }

    private void next() {
        Toggle();
        boolean ok = true;
        do {
            ok=true;
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toUpperCase();
            if (input.length()<1)
                ok=false;
            else
                switch (input.charAt(0)) {
                    case 'D': {
                        if (character.col>= ColN - 1) {
                            ok = false;
                            break;
                        } else {
                            character.col+=1;
                            try{
                                entityMap[character.row][character.col].enter();
                            }
                            catch(IllegalAccessException e){
                                System.out.println(e.getMessage());
                                ok=false;
                                character.col -= 1;
                            }
                            catch(InterruptedException e){
                                InterruptedExceptionHandler(e);
                            }
                        }
                        break;
                    }
                    case 'A': {
                        if (character.col <= 0 ) {
                            ok = false;
                            break;
                        } else {
                            character.col -= 1;
                            try{
                                entityMap[character.row][character.col].enter();
                            }
                            catch(IllegalAccessException e){
                                System.out.println(e.getMessage());
                                ok=false;
                                character.col += 1;
                            }
                            catch(InterruptedException e){
                                InterruptedExceptionHandler(e);
                            }
                        }
                        break;
                    }
                    case 'S': {
                        if (character.row >= RowN - 1) {
                            ok = false;
                            break;
                        } else {
                            character.row += 1;
                            try{
                                entityMap[character.row][character.col].enter();
                            }
                            catch(IllegalAccessException e){
                                System.out.println(e.getMessage());
                                ok=false;
                                character.row -= 1;
                            }
                            catch(InterruptedException e){
                                InterruptedExceptionHandler(e);
                            }
                        }
                        break;
                    }
                    case 'W': {
                        if (character.row <= 0) {
                            ok = false;
                            break;
                        } else {
                            character.row -= 1;
                            try{
                                entityMap[character.row][character.col].enter();
                            }
                            catch(IllegalAccessException e){
                                System.out.println(e.getMessage());
                                ok=false;
                                character.row += 1;
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
                    default: {
                        ok=false;
                        break;
                    }
                }
        }
        while (!ok);
        Update();
    }

    public void changePosition(MoveableEntity subject, int newRow, int newCol){
        subject.getOriginal().row = subject.getRealEntity().row;
        subject.getOriginal().col = subject.getRealEntity().col;
        entityMap[subject.getRealEntity().row][subject.getRealEntity().col] = subject.getOriginal();
        subject.getRealEntity().row=newRow;
        subject.getRealEntity().col=newCol;
        subject.setOriginal(entityMap[newRow][newCol]);
        subject.getOriginal().row=newRow;
        subject.getOriginal().col=newCol;
        entityMap[newRow][newCol] = subject.getRealEntity();
    }
}
