import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FlashcardsCLI {
    private Scanner scanner;

    public FlashcardsCLI() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        FlashcardsCLI flashcardsCLI = new FlashcardsCLI();

        System.out.println("\n****************************");
        System.out.println(" Welcome to Flashcards CLI! ");
        System.out.println("****************************\n");

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Study");
            System.out.println("2. Edit my collections");
            System.out.println("3. Quit");

            System.out.print("Enter your choice (1-3): ");
            String choice = flashcardsCLI.scanner.nextLine();

            switch (choice) {
                case "1":
                    flashcardsCLI.study();
                    break;
                case "2":
                    flashcardsCLI.edit();
                    break;
                case "3":
                    System.out.println("Bye bye!");
                    flashcardsCLI.scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private void study() {
        String deckName = deckSelect();   
    }

    private void edit() {
        System.out.println("1. Create new deck");
        System.out.println("2. Edit my collection");
        System.out.print("Enter your choice (1-2): ");
        
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                String deckName = createFile();
                inputCard(deckName);
                break;
            case "2":
                String deckName2 = deckSelect();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }

    private void inputCard(String fileName){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            HashMap<String, String> capitalCities = new HashMap<String, String>();

            capitalCities.put("England", "London");
            capitalCities.put("Germany", "Berlin");
            capitalCities.put("Norway", "Oslo");
            capitalCities.put("USA", "Washington DC");

            for (String key : capitalCities.keySet()) {
                myWriter.write(key + ": " + capitalCities.get(key) + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void editCard(String fileName){

    }

    private void readCard(String fileName){

    }

    private String createFile(){
            System.out.println("input deck name:");
            String name = scanner.nextLine();
            try{
                File myFile = new File(name + ".txt");
                if(myFile.createNewFile()){
                    System.out.println("file created: "  + myFile.getName()) ;
                    return myFile.getName();
                }else{
                    System.out.println("File already exists");
                }
            }catch(IOException e){
                System.out.println("an error occurred");
                e.printStackTrace();
            }
        return "";
    }

    private String deckSelect() {
        try {
            File myObj = new File("deck.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

            System.out.print("Please enter deck name: ");
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the deck file: " + e.getMessage());
        }
        return "";
    }
}