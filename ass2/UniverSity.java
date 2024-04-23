package ass2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UniverSity {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(1978, 9, 1);
        LocalDate endDate = LocalDate.of(1988, 6, 1);
        int gradePointer = 0;
        AtomicInteger total = new AtomicInteger(0);

        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            gradePointer++;
            LocalDate yearStartDate = LocalDate.of(year, 9, 1);
            LocalDate yearEndDate = LocalDate.of(year + 1, 6, 1);
    
            TimeCalculate timeCalculate = new TimeCalculate(yearStartDate, yearEndDate);
            Map<DayOfWeek, Integer> weekdayCounts = timeCalculate.countWeekdays();
            long weeks = timeCalculate.countWeeks();
    
            AtomicInteger total_year = new AtomicInteger(0);

                Map<DayOfWeek, Integer> schoolTimes = schoolSmall();
                weekdayCounts.forEach((dayOfWeek, count) -> {
                    int learning_hours = schoolTimes.get(dayOfWeek);
                    System.out.printf("%s: %d хичээлийн цаг%n", dayOfWeek, count * learning_hours);
                    total_year.addAndGet(count * learning_hours);
                    total.addAndGet(count * learning_hours);)}
    
            System.out.println(year + " -ээс " + year + "он");
            System.out.println(gradePointer + "-р ангид суралцах нийт хичээлийн цаг: " + total_year.get());
            System.out.println("нийт 7 хоногийн тоо: " + weeks);
            System.out.println();
        }
        System.out.println("Нийт суралцсан цаг: " + total.get() * 0.75 + " цаг");
    }
    
    public static Map<DayOfWeek, Integer> schoolSmall() {
        Map<DayOfWeek, Integer> schoolTimes = new HashMap<>();
        schoolTimes.put(DayOfWeek.MONDAY, 3);
        schoolTimes.put(DayOfWeek.TUESDAY, 3);
        schoolTimes.put(DayOfWeek.WEDNESDAY,3);
        schoolTimes.put(DayOfWeek.THURSDAY, 3);
        schoolTimes.put(DayOfWeek.FRIDAY, 3);
        schoolTimes.put(DayOfWeek.SATURDAY, 3);
        schoolTimes.put(DayOfWeek.SUNDAY, 0);
        return schoolTimes;
    }

}
