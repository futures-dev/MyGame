package Engine;

import java.util.Scanner;

/**
 * Created by Computer on 20.09.2015.
 */
public class Loader {
    private Entities.Character character;

    public Loader() {
        System.out.println("Welcome to My Game!");
        System.out.println("For better experience maximize the console window, please.");
        System.out.print("Print your name, please: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Great, " + name + ". Let's go for the adventure.");
        character = new Entities.Character(name);
    }
}
