package ass2;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class School {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(1978, 9, 1);
        LocalDate endDate = LocalDate.of(1992, 6, 1);
        int gradePointer = 0;
        AtomicInteger total = new AtomicInteger(0);
        AtomicInteger totalUniversity = new AtomicInteger(0);
        AtomicInteger interesting = new AtomicInteger(0);
        AtomicInteger workTime = new AtomicInteger(0);

        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            gradePointer++;
            LocalDate yearStartDate = LocalDate.of(year, 9, 1);
            LocalDate yearEndDate = LocalDate.of(year + 1, 6, 1);
    
            TimeCalculate timeCalculate = new TimeCalculate(yearStartDate, yearEndDate);
            Map<DayOfWeek, Integer> weekdayCounts = timeCalculate.countWeekdays();
            int weeks =(int) timeCalculate.countWeeks();
    
            AtomicInteger total_year = new AtomicInteger(0);
            if (gradePointer >= 1 && gradePointer <= 3) {
                Map<DayOfWeek, Integer> schoolTimes = schoolSmall();
                weekdayCounts.forEach((dayOfWeek, count) -> {
                    int learning_hours = schoolTimes.get(dayOfWeek);
                    int minutes = count * learning_hours * 45;
                    System.out.printf("%s: %d хичээллэсэн хугацаа (цаг)%n", dayOfWeek, minutes/60);
                    total_year.addAndGet(minutes);
                    total.addAndGet(minutes);
                });
                interesting.addAndGet(weeks*4*45);
            } else if (gradePointer >= 4 && gradePointer <= 8) {
                int learning_hours_week = 34;
                int minutes = weeks * learning_hours_week * 45;
                total_year.addAndGet((int) (minutes));
                total.addAndGet((int) (minutes));
                interesting.addAndGet(weeks*5*45);
            } else if (gradePointer >= 9 && gradePointer <= 10) {
                Map<DayOfWeek, Integer> schoolTimes = schoolHigh();
                weekdayCounts.forEach((dayOfWeek, count) -> {
                    int learning_hours = schoolTimes.get(dayOfWeek);
                    int minutes = count * learning_hours * 45;
                    System.out.printf("%s: %d хичээллэсэн хугацаа (цаг)%n", dayOfWeek, minutes/60);
                    total_year.addAndGet(minutes);
                    total.addAndGet(minutes);
                });
                interesting.addAndGet(weeks*4*45);
            }else if (gradePointer >= 11 && gradePointer <= 15) { 
                Map<DayOfWeek, Integer> schoolTimes = University();
                weekdayCounts.forEach((dayOfWeek, count) -> {
                    int learning_hours = schoolTimes.get(dayOfWeek);
                    int minutes = count * learning_hours * 90;
                    System.out.printf("%s: %d хичээллэсэн хугацаа (цаг)%n", dayOfWeek, minutes/60);
                    total_year.addAndGet(minutes);
                    totalUniversity.addAndGet(minutes);
                });
                Map<DayOfWeek, Integer> work = workDay();
                int totalWorkMinutes = 0;
                for (int i = 0; i < 7; i++) {
                    DayOfWeek day = DayOfWeek.of((i % 7) + 1);
                    totalWorkMinutes += work.get(day) * 60 ;
                }
                workTime.addAndGet(totalWorkMinutes);
            }
    
            System.out.println(year + " -ээс " + (year+1) + "он");
            if (gradePointer > 10){
                System.out.println((gradePointer-10) + "-р курсэд суралцах нийт хичээлийн цаг " + total_year.get()/60);
            }else{
                System.out.println(gradePointer + "-р ангид суралцах нийт хичээлийн цаг: " + total_year.get()/60);
            }
            
            System.out.println("нийт 7 хоногийн тоо: " + weeks);
            System.out.println();
        }
        System.out.println("Нийт дунд сургуульд суралцсан цаг: " + total.get()/60 + " цаг");
        System.out.println("Сонирхлоор суралцсан цаг: " + interesting.get()/60 + " цаг");
        System.out.println("Дээд сургуульд суралцсан цаг: " + totalUniversity.get()/60 + " цаг");
        System.out.println("Ажилсан цаг: " + workTime.get()/60 + " цаг");
    }
    
    public static Map<DayOfWeek, Integer> schoolSmall() {
        Map<DayOfWeek, Integer> schoolTimes = new HashMap<>();
        schoolTimes.put(DayOfWeek.MONDAY, 4);
        schoolTimes.put(DayOfWeek.TUESDAY, 4);
        schoolTimes.put(DayOfWeek.WEDNESDAY, 4);
        schoolTimes.put(DayOfWeek.THURSDAY, 4);
        schoolTimes.put(DayOfWeek.FRIDAY, 4);
        schoolTimes.put(DayOfWeek.SATURDAY, 4);
        schoolTimes.put(DayOfWeek.SUNDAY, 0);
        return schoolTimes;
    }

    public static Map<DayOfWeek, Integer> schoolMedium() {
        Map<DayOfWeek, Integer> schoolTimes = new HashMap<>();
        schoolTimes.put(DayOfWeek.MONDAY, 5);
        schoolTimes.put(DayOfWeek.TUESDAY, 5);
        schoolTimes.put(DayOfWeek.WEDNESDAY, 5);
        schoolTimes.put(DayOfWeek.THURSDAY, 5);
        schoolTimes.put(DayOfWeek.FRIDAY, 5);
        schoolTimes.put(DayOfWeek.SATURDAY, 0);
        schoolTimes.put(DayOfWeek.SUNDAY, 0);
        return schoolTimes;
    }

    public static Map<DayOfWeek, Integer> schoolHigh() {
        Map<DayOfWeek, Integer> schoolTimes = new HashMap<>();
        schoolTimes.put(DayOfWeek.MONDAY, 6);
        schoolTimes.put(DayOfWeek.TUESDAY, 6);
        schoolTimes.put(DayOfWeek.WEDNESDAY, 6);
        schoolTimes.put(DayOfWeek.THURSDAY, 6);
        schoolTimes.put(DayOfWeek.FRIDAY, 6);
        schoolTimes.put(DayOfWeek.SATURDAY, 6);
        schoolTimes.put(DayOfWeek.SUNDAY, 0);
        return schoolTimes;
    }

    public static Map<DayOfWeek, Integer> University() {
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

    public static Map<DayOfWeek, Integer> workDay() {
        Map<DayOfWeek, Integer> workTimes = new HashMap<>();
        workTimes.put(DayOfWeek.MONDAY, 8);
        workTimes.put(DayOfWeek.TUESDAY, 8);
        workTimes.put(DayOfWeek.WEDNESDAY, 8);
        workTimes.put(DayOfWeek.THURSDAY, 8);
        workTimes.put(DayOfWeek.FRIDAY, 8);
        workTimes.put(DayOfWeek.SATURDAY, 6);
        workTimes.put(DayOfWeek.SUNDAY, 0);
        return workTimes;
    }
}
