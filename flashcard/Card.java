package flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Card implements CardInterface {
    private Scanner scanner;

    public Card() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String FilterCollection() {
        try {
            File folder = new File(".");
            File[] files = folder.listFiles();

            System.out.println("Available collections:");
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName().replace(".txt", ""));
                }
            }

            System.out.print("Please enter collection name: ");
            return scanner.nextLine() + ".txt";
        } catch (Exception e) {
            System.out.println("An error occurred while listing collections: " + e.getMessage());
        }
        return "";
    }

    @Override
    public String CreateCollection() {
        try {
            System.out.println("Input deck name:");
            String name = scanner.nextLine();
            String fullName = name + ".txt";

            File myFile = new File(fullName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                return fullName;
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the collection: " + e.getMessage());
        }
        return "";
    }

    @Override
    public void SetCard(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName, true); // Append mode
            System.out.print("question:\t");
            String question = scanner.nextLine();
            System.out.print("answer:\t");
            String answer = scanner.nextLine();

            myWriter.write(question + ":\t" + answer + "\n");
            myWriter.close();
            System.out.println("Successfully added card to the collection.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the card: " + e.getMessage());
        }
    }

    @Override
    public void GetCard(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String flashcard = fileScanner.nextLine();
                System.out.println(flashcard);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public void UpdateCard(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder updatedContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                String flashcard = scanner.nextLine();

                String[] parts = flashcard.split(":\t");
                String question = parts[0];
                String answer = parts[1];

                System.out.println("question: " + question + "\tanswer: " + answer);
                System.out.println("1. delete");
                System.out.println("2. update");
                System.out.println("3. skip");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        // Delete card
                        break;
                    case "2":
                        // Update card
                        break;
                    case "3":
                        updatedContent.append(flashcard).append("\n");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                        break;
                }
            }

            scanner.close();

            FileWriter writer = new FileWriter(fileName);
            writer.write(updatedContent.toString());
            writer.close();

            System.out.println("Flashcards updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file: " + e.getMessage());
        }
    }
}
