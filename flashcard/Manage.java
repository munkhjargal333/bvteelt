package app;

import java.util.Scanner;

public class Manage implements ManageInterface {
    private Scanner scanner;

    public Manage() {
        this.scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Manage manage = new Manage();
        manage.start();
       //Card card = new Card();
       //carf.SetCard(test);
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
    
    public void card(){
        System.out.println("1. Create new deck");
        System.out.println("2. Edit my collection");
        System.out.println("3. back");
        System.out.print("Enter your choice (1-3): ");
        
        Card card = new Card();
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                String setname = card.CreateCollection();

                boolean l = true;
                while(l){
                    System.out.println("1. add card");
                    System.out.println("2. back");

                    String ans = scanner.nextLine();
                    switch (ans) {
                        case "1":
                            card.SetCard(ans);
                            break;
                        case "2":
                            l= false;
                            break;
                        default:
                            System.out.print("Enter your choice (1-3): ");
                    }
                }
                break;
            case "2":
                String collname = card.FilterCollection();
                card.UpdateCard(collname);
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }

    }

    public void play(){

    }
    public void study() {
        
    }
}