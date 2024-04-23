package ass2;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Work {

    private Map<DayOfWeek, Integer> workTime;

    public Work(Map<DayOfWeek, Integer> workTime) {
        this.workTime = workTime;
    }

    public int getWorkTime(DayOfWeek dayOfWeek) {
        return workTime.get(dayOfWeek);
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(1978, 9, 1);
        LocalDate endDate = LocalDate.of(1979, 6, 1);

        TimeCalculate timeCalculate = new TimeCalculate(startDate, endDate);
        Map<DayOfWeek, Integer> weekdayCounts = timeCalculate.countWeekdays();

        Work work = new Work(workDay());

        System.out.println("counts:");
        AtomicInteger total = new AtomicInteger(0);
        weekdayCounts.forEach((dayOfWeek, count) -> {
            int workHours = work.getWorkTime(dayOfWeek);
            System.out.println(dayOfWeek + ": " + count * workHours + " hours");
            total.addAndGet(count * workHours);
        });

        System.out.println("total worked: " + total.get() + " hours");
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
