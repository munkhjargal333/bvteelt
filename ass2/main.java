package ass2;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(1978, 9, 1);
        LocalDate endDate = LocalDate.of(1988, 9, 1);

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            System.out.println(currentDate.getDayOfWeek().getValue());
            currentDate = currentDate.plusDays(1);
        }
    }
    
}
