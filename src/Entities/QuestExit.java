package Entities;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Computer on 27.09.2015.
 */
public class QuestExit extends Exit {
    public QuestExit(int ID, String name, Level level, char symbol, int posRow, int posCol, String onEnterMsg, LinkedList<Item> keysNeeded, LinkedList<Item> items, String illegalAccessString) {
        super(ID, name, level, symbol, posRow, posCol, onEnterMsg, keysNeeded, items, illegalAccessString);
        choices = new String[]{
                "- Who are you?", // 0 0 0
                "- I was afraid.", // 0 1 1
                "- I was in a hurry.", // 0 2 2
                "- My wife doesn't let me talk to women :(", // 1 0 3
                "- I didn't notice you, sorry.", // 1 1 4
                "- You didn't look like the one to be interested in.", // 1 2 5
                "Run away in fear.", // 2 0 6
                "- I don't care actually.", // 2 1 7
                "- I am not a fool, you stupid girl.", // 2 2 8
                "- I don't remember... I woke up in that building and wanna get home.", // 3 0 9
                "- I can see fog far there. Wanna find out what's there.", // 3 1 10
                "- It's not your business", // 3 2 11
                "Run away in fear.", // 4 0 12
                "- I don't care actually.", // 4 1 13
                "- I am not a fool, you stupid girl.", // 4 2 13
                "- The weather's fine, isn't it?", // 5 0 14
                "- Well, so, I gotta go to the exit. See ya.", // 5 1 15
                "- Wow, you've shut up at last.", // 5 2 16
                "Run away in fear.", // 6 0 17
                "- I don't care actually.", // 6 1 18
                "- I am not a fool, you stupid girl.", // 6 2 19
                "", "", "", // 7 22
                "Run away in fear", // 8 0 23
                "Accept your death", // 8 1 24
                "Defend yourself", // 8 2 25
                "", "", "", // 9 28
                "", "", "", // 10 31
                "", "", "" // 11 34

        };
        monologues = new String[]{
                "A pretty girl behind you shouts: \"Hey, wait! Why did you ignore me?\"", // 0
                "- I was staying alone in the center of the building. How could you not get interested in me and passed by?", // 1
                "- Ha-ha, you are afraid. So foolish. I hate fools.",  // 2
                "- Where are you heading to?", // 3
                "- Ha-ha, you are such a looser. I hate loosers.", // 4
                "The girl is standing still in silence.", // 5
                "You are so foolish that you can't realize my perfection! What a fool you are, I hate you!", // 6
                "You ran towards the exit, which is 300 metres away. The girl immediately decided to follow and catch you. Hurry up!", // 7
                "- Then you must die, ignorant indifferent man!", // 8
                "The girl took a dagger and killed you with pleasure", // 9
                "You'll never get back home!\nThe girl took a dagger and killed you with pleasure.", // 10
                "I won't let you go there. Hey, stop running away! I'll catch you!\nYou ran towards the exit, which is 300 metres away. The girl immediately decided to follow and catch you. Hurry up!", // 11

        };
        actions = new int[]{
                1, 2, 3, //0
                4, 5, 6, //1
                7, 8, 9, //2
                10, 11, 6, //3
                7, 8, 9, //4
                9, 11, 6, //5
                7, 8, 9, //6
                -2, -2, -2, //7
                7, 9, 9, //8
                -1, -1, -1, //9
                -1, -1, -1, //10
                -2, -2, -2 //11
        };
    }

    String[] choices;
    String[] monologues;
    int[] actions;

    @Override
    public void enter() throws IllegalAccessException, InterruptedException {
        super.enter();
        int action = 0;
        boolean ok = true;
        do {
            switch (action) {
                case -1:
                case 9:
                case 10:
                    throw new InterruptedException("You are dead :( Try again, please");
                case -2:
                case 7:
                case 11:
                    run();
                    ok = false;
                    break;
                default:
                    System.out.println(monologues[action]);
                    System.out.println("(1) " + choices[action * 3]);
                    System.out.println("(2) " + choices[action * 3 + 1]);
                    System.out.println("(3) " + choices[action * 3 + 2]);
                    Scanner scanner = new Scanner(System.in);
                    int A = 0;
                    do {
                        String input = scanner.nextLine();
                        try {
                            A = Integer.parseInt((input));
                        } catch (NumberFormatException e) {
                            continue;
                        }
                    } while (A < 1 || A > 3);
                    action = actions[action * 3 + A - 1];
            }
        }
        while (ok);
        System.out.println("You've run away and reached the exit from the level!");
    }

    static Random random = new Random();

    private class Runner {
        private int stamina = 80 + random.nextInt(21);
        private int speed = random.nextInt(5) + 1;
        private int minSpeed = 1;
        private int maxSpeed = random.nextInt(5) + 36;
        private int shoes = 80 + random.nextInt(21);
        private int current = 0;
        private int destination = 400;

        public void repair() {
            setStamina(getStamina() + 50 + random.nextInt(5) * 10);
            setShoes(getShoes() + 50 + random.nextInt(5) * 10);
        }

        public void decSpeed() {
            setSpeed(getSpeed() - random.nextInt(5));
        }

        public void wear() {
            setShoes(getShoes() - random.nextInt(5) - 5);
            setStamina(getShoes() - random.nextInt(5) - 5);
        }

        public void wearLess() {
            setShoes(getShoes() - random.nextInt(5));
            setStamina(getShoes() - random.nextInt(5));
        }

        public void incCurrent() {
            setCurrent(getCurrent() + getSpeed());
        }

        public void incSpeed() {
            setSpeed(getSpeed() + random.nextInt(5));
        }

        public int getToGo() {
            return getDestination() - getCurrent();
        }

        public int getStamina() {
            return stamina;
        }

        public void setStamina(int stamina) {
            this.stamina = stamina;
            if (this.stamina < 0)
                this.stamina = 0;
            else if (this.stamina > 100)
                this.stamina = 100;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
            if (this.speed > maxSpeed)
                this.speed = maxSpeed;
            else if (this.speed < minSpeed)
                this.speed = minSpeed;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }

        public int getShoes() {
            return shoes;
        }

        public void setShoes(int shoes) {
            this.shoes = shoes;
            if (this.shoes < 0)
                this.shoes = 0;
            else if (this.shoes > 100)
                this.shoes = 100;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getDestination() {
            return destination;
        }

        public int getMinSpeed() {
            return minSpeed;
        }
    }

    private class GirlRunner {
        private Runner runner;
        private int speed = random.nextInt(5) + 3;
        private int maxSpeed = random.nextInt(5) + 21;
        private int current = -40;

        public GirlRunner(Runner runner) {
            this.runner = runner;
        }

        public void incCurrent() throws InterruptedException {
            setCurrent(getCurrent() + getSpeed());
            setSpeed(getSpeed() + random.nextInt(5));
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
            if (this.speed > maxSpeed)
                this.speed = maxSpeed;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) throws InterruptedException {
            this.current = current;
            if (this.current >= runner.current) {
                throw new InterruptedException("The girl caught you and executed a violent assassination.");
            }
        }
    }

    private void run() throws InterruptedException {
        Runner runner = new Runner();
        GirlRunner girl = new GirlRunner(runner);
        String option1 = "(1) Try to run faster";
        String option2 = "(2) Just keep running";
        String option3 = "(3) Slow down";
        String option4 = "(4) Stop and repair my shoes";
        while (runner.getToGo() > 0) {
            System.out.println("Your stamina: " + runner.getStamina());
            System.out.println("Your speed: " + runner.getSpeed());
            System.out.println("Shoes are " + runner.getShoes() + " percent unbroken");
            System.out.println("You have " + runner.getToGo() + "m to go");
            System.out.println("The girl is " + (runner.getCurrent() - girl.getCurrent()) + "m behind you.");
            System.out.println("What will you do?");
            System.out.println(option1);
            System.out.println(option2);
            System.out.println(option3);
            System.out.println(option4);
            Scanner scanner = new Scanner(System.in);
            int A;
            boolean bad = false;
            do {
                String input = scanner.nextLine();
                try {
                    A = Integer.parseInt((input));
                    bad = false;
                    switch (A) {
                        case 1:
                            if (runner.getSpeed() >= runner.getMaxSpeed()) {
                                System.out.println("You've reached your max speed, so you just keep running");
                            } else if (runner.getStamina() > 0 && runner.getShoes() > 0) {
                                runner.incCurrent();
                                runner.incSpeed();
                                runner.wear();
                                break;
                            }
                        case 2:
                            if (runner.getStamina() > 0 && runner.getShoes() > 0) {
                                runner.incCurrent();
                                runner.incSpeed();
                                runner.wear();
                                break;
                            }
                        case 3:
                            if (runner.getStamina() > 0 && runner.getShoes() > 0) {
                                runner.incCurrent();
                                runner.decSpeed();
                                runner.wearLess();
                                break;
                            } else if (runner.getStamina() > 0)
                                System.out.println("Your shoes are broken, so you had to stop and repair them.");
                            else
                                System.out.println("You are too exhausted, so you had to stop.");
                        case 4:
                            runner.incCurrent();
                            runner.setSpeed(runner.getSpeed() / 4);
                            runner.repair();
                            break;
                        default:
                            bad = true;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            } while (bad);
            girl.incCurrent();
        }

    }


}
