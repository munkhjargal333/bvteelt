package ass2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class TimeCalculate {
    private LocalDate startDate;
    private LocalDate endDate;

    public TimeCalculate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Map<DayOfWeek, Integer> countWeekdays() {
        Map<DayOfWeek, Integer> weekdayCounts = new HashMap<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            weekdayCounts.put(dayOfWeek, weekdayCounts.getOrDefault(dayOfWeek, 0) + 1);
            currentDate = currentDate.plusDays(1);
        }
        return weekdayCounts;
    }

    public long countWeeks() {
        return ChronoUnit.WEEKS.between(startDate, endDate);
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(1978, 9, 1);
        LocalDate endDate = LocalDate.of(1979, 6, 1);

        TimeCalculate timeCalculate = new TimeCalculate(startDate, endDate);
        Map<DayOfWeek, Integer> weekdayCounts = timeCalculate.countWeekdays();
        long weeks = timeCalculate.countWeeks();

        System.out.println("Weekday counts:");
        for (Map.Entry<DayOfWeek, Integer> entry : weekdayCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Weeks between start and end date: " + weeks);
    }
}
