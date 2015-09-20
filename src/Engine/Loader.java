package Engine;

import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
public class Loader {
    private Entities.Character character;
    public Loader(){
        System.out.println("Welcome to My Game!");
        System.out.print("Print your name, please: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Great, "+name+". Let's go for level 1.");
        character = new Entities.Character(name);
    }
}
