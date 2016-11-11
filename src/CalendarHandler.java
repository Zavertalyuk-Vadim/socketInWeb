import print.Print;
import print.PrintInWeb;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 11/11/16.
 */
public class CalendarHandler implements Handler {
    private HttpRequest request;

    public CalendarHandler(HttpRequest request) {
        this.request = request;
    }

    @Override
    public String print(HttpRequest httpRequest) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        System.out.println(httpRequest.getParam().get("day"));
        System.out.println(httpRequest.getParam().get("month"));
        System.out.println(httpRequest.getParam().get("year"));
        System.out.println(httpRequest.getParam().get("dayOfWeek"));
        String weekend = httpRequest.getParam().get("weekends");
        List<DayOfWeek> weekList = add(weekend, dayOfWeeks);
        Print print = new PrintInWeb();
        print.setToday(LocalDate.of(Integer.parseInt(httpRequest.getParam().get("year")), Integer.parseInt(httpRequest.getParam().get("month")), Integer.parseInt(httpRequest.getParam().get("day"))));
        print.setDayOfWeek(DayOfWeek.of(Integer.parseInt(httpRequest.getParam().get("dayOfWeek"))));
        print.setWeekends(weekList);
        return print.print();
    }

    private static List<DayOfWeek> add(String s, List<DayOfWeek> dayOfWeeks) {
        String[] numbersArray = s.split("[, ]");
        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                DayOfWeek weekends = DayOfWeek.of(Integer.parseInt(number.trim()));
                dayOfWeeks.add(weekends);
            }
        }
        return dayOfWeeks;
    }
}