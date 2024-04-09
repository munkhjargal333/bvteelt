package ass2;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(1978, 9, 1); // Start date
        LocalDate endDate = LocalDate.of(1988, 6, 1);   // End date

        School school = new School(5);       

        init(school);

        Map<Integer, Long> yearLearningTimes = calculateYearlyLearningTimes(school, startDate, endDate, 1);
        System.out.println("Yearly learning times:");
        for (Map.Entry<Integer, Long> entry : yearLearningTimes.entrySet()) {
            System.out.println("Year " + entry.getKey() + ": " + entry.getValue() + " minutes");
        }
    }

    public static void init(School school) {
        school.setGradeLearningTime(1, 60);
        school.setGradeLearningTime(2, 90);
        school.setGradeLearningTime(3, 120);
        school.setGradeLearningTime(4, 150);
        school.setGradeLearningTime(5, 180);
        school.setGradeLearningTime(6, 210);
        school.setGradeLearningTime(7, 240);
        school.setGradeLearningTime(8, 270);
        school.setGradeLearningTime(9, 300);
        school.setGradeLearningTime(10, 330);
        school.setGradeLearningTime(11, 360);
        school.setGradeLearningTime(12, 390);
    }

    public static Map<Integer, Long> calculateYearlyLearningTimes(School school, LocalDate startDate, LocalDate endDate, int grade) {
        Map<Integer, Long> yearLearningTimes = new HashMap<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            int year = currentDate.getYear();
            long totalLearningTime = 0;

            while (!currentDate.isAfter(endDate) && currentDate.getYear() == year) {
                totalLearningTime += school.getGradeLearningTime(grade);
                currentDate = currentDate.plusDays(1);
            }

            yearLearningTimes.put(year, totalLearningTime);
        }

        return yearLearningTimes;
    }
}
