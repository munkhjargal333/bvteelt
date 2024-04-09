package grades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * A GradeManager will create a command-line prompt that will let someone add grades
 * and display grades in histogram format.
 */
public class GradeManager {

    // Keeps track of the number of each grade this has
    private HashMap<LetterGrade, Integer> allGrades;

    /**
     * Creates a new GradeManager.
     */
    public GradeManager() {
        // Create a new HashMap of the grades
        this.allGrades = new HashMap<LetterGrade, Integer>();

        // Add in all grades and set the occurrence to 0
        for (LetterGrade gl : LetterGrade.values()) {
            allGrades.put(gl, 0);
        }
    }

    /**
     * Adds grade to this GradeManager.
     *
     * @param grade - grade to add to this grad manager
     */
    public void addGrade(String grade) throws InvalidGradeException {
        if (grade.equals("a")) {
            allGrades.put(LetterGrade.A, allGrades.get(LetterGrade.A) + 1);
        } else if (grade.equals("b")) {
            allGrades.put(LetterGrade.B, allGrades.get(LetterGrade.B) + 1);
        } else if (grade.equals("c")) {
            allGrades.put(LetterGrade.C, allGrades.get(LetterGrade.C) + 1);
        } else if (grade.equals("d")) {
            allGrades.put(LetterGrade.D, allGrades.get(LetterGrade.D) + 1);
        } else if (grade.equals("f")) {
            allGrades.put(LetterGrade.F, allGrades.get(LetterGrade.F) + 1);
        } else {
            throw new InvalidGradeException("Invalid grade: " + grade);
        }
    }

    /**
     * Prints out a histogram of the grades to the console.
     */
    public void printHistogram() {
        for (LetterGrade gl : LetterGrade.values()) {
            System.out.print(gl + ": ");
            for (int i = 0; i < allGrades.get(gl); i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Returns a string representation of the histogram of the grades.
     *
     * @return a string representation of the histogram of the grades.
     */
    public String getHistString() {
        StringBuilder sb = new StringBuilder();
        for (LetterGrade gl : LetterGrade.values()) {
            sb.append(gl).append(":");
            for (int i = 0; i < this.allGrades.get(gl); i++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Simple loop that accepts 3 commands from System.in:
     * add <some grade> : for example, "add a" or "add b"
     * adds the given grade to the GradeManager
     * print            : prints out all the grades in this GradeManager
     * in a histogram format
     * exit             : exits the program
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        GradeManager gm = new GradeManager();

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Starting the grade manager");

        while (true) {
            String input = cin.readLine();
            if (input.startsWith("add")) {
                String[] tokens = input.split(" ");
                if (tokens.length != 2) {
                    System.out.println("Invalid input. Use format: add <grade>");
                    continue;
                }
                try {
                    gm.addGrade(tokens[1]);
                } catch (InvalidGradeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.equals("print")) {
                gm.printHistogram();
            } else if (input.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }

        }
    }

}
