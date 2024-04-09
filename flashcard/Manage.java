package flashcard;

import java.util.Scanner;

public class Manage implements ManageInterface {
    private Scanner scanner;

    public Manage() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Manage manage = new Manage();
        manage.start();
    }

    public void start() {
        System.out.println("\n****************************");
        System.out.println(" Welcome to Flashcards CLI! ");
        System.out.println("****************************\n");

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Study");
            System.out.println("2. Edit my collections");
            System.out.println("3. Quit");

            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    play();
                    break;
                case "2":
                    card();
                    break;
                case "3":
                    System.out.println("Bye bye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    public void card() {
        System.out.println("1. Create new collection");
        System.out.println("2. Edit my collection");
        System.out.println("3. Back");
        System.out.print("Enter your choice (1-3): ");

        Card card = new Card();
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                String setName = card.CreateCollection();

                boolean loop = true;
                while (loop) {
                    System.out.println("1. Add card");
                    System.out.println("2. Back");

                    String ans = scanner.nextLine();
                    switch (ans) {
                        case "1":
                            card.SetCard(setName);
                            break;
                        case "2":
                            loop = false;
                            break;
                        default:
                            System.out.print("Invalid choice. Please enter 1 or 2: ");
                    }
                }
                break;
            case "2":
                String collName = card.FilterCollection();
                card.UpdateCard(collName);
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }

    public void play() {
        Play play = new Play();
        System.out.println("Enter the name of the collection you want to study:");
        String fileName = scanner.nextLine();
        play.start(fileName);
    }
}
