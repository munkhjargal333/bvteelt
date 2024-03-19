package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Card implements CardInterface {
    private Scanner scanner;

    public Card() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String FilterCollection() {
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

    @Override
    public String CreateCollection() {
        System.out.println("Input deck name:");
        String name = scanner.nextLine();
        //FileWriter myWriter = new FileWriter("");

        try {
            String fullname = name + ".txt";
            File myFile = new File(fullname);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                return fullname;
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return "";
    }
    @Override
    public void SetCard(String fileName) {
        try {
            File file = new File(fileName);
        
            if (file.exists()) {
                System.out.println("File exists");
            } else {
                System.out.println("File does not exist");
            }

            FileWriter myWriter = new FileWriter(fileName);
            
            System.out.print("question:\t");
            String question = scanner.nextLine();

            System.out.print("answer:\t");
            String answer = scanner.nextLine();

            myWriter.write(question + answer);
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void GetCard(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
    
            while (scanner.hasNextLine()) {
                String flashcard = scanner.nextLine();
                System.out.println(flashcard);
            }
    
            scanner.close();
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
    
                System.out.println("question: " + question + "\tanswer" + answer);

                System.out.println("1. delete");
                System.out.println("2. update");
                System.out.println("3. next");

                String a = scanner.nextLine();

                switch (a) {
                    case "1":
                        
                        break;
                    case "2":
                        break;
                    case "3":
                        updatedContent.append(flashcard);
                        return;
                    default:
                        System.out.print("!!!sorry default choose next option!!! ");
                        return;
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
