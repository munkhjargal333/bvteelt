
package ass2;
import java.util.HashMap;
import java.util.Map;

public class School {
    private Map<Integer, Integer> gradeLearningTimes; // Map<Grade, LearningTime>
    private int learningDays;

    /*
     * 1 бага анги
     * 2 дунд анги
     * 3 ахлах анги
     * 4 дээд сургууль
     * 
    */

    public School(int learningDays) {
        gradeLearningTimes = new HashMap<>();
        this.learningDays = learningDays;
    }

    public void setGradeLearningTime(int grade, int learningTimeMinutes) {
        gradeLearningTimes.put(grade, learningTimeMinutes);
    }

    public int getGradeLearningTime(int grade) {
        return gradeLearningTimes.getOrDefault(grade, 0);
    }

    public int getLearningDays() {
        return learningDays;
    }

    public static void main(String[] args) {
        int learningDays = 5;
        School school = new School(learningDays);

        school.setGradeLearningTime(1, 60); 
        school.setGradeLearningTime(6, 45); 
        school.setGradeLearningTime(9, 75); // Grade 9 learns for 75 minutes
        school.setGradeLearningTime(12, 90); // Grade 12 learns for 90 minutes

        int grade = 1;
        System.out.println("Students in grade " + grade + " must learn for " + school.getGradeLearningTime(grade) + " minutes.");
        System.out.println("All grades learn for " + school.getLearningDays() + " days.");
    }
}
