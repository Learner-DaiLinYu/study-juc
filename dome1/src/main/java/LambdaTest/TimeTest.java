package LambdaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeTest {
    public static void main(String[] args) {
        //format yyyy-MM-dd
        LocalDate date = LocalDate.now();
        System.out.printf("date format : %s%n", date);

        //format HH:mm:ss
        LocalTime time = LocalTime.now().withNano(0);
        System.out.printf("time format : %s%n", time);

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        //System.out.println(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTime.format(dateTimeFormatter);
        System.out.printf("dateTime format : %s%n", dateTimeStr);

        //时间转化
        LocalDate date1 = LocalDate.of(2021, 1, 26);
        LocalDate parse = LocalDate.parse("2021-01-26");
        System.out.println(date1.equals(parse));

        LocalDateTime dateTime1 = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        LocalDateTime parse1 = LocalDateTime.parse("2021-01-26T12:12:22");
        System.out.println(dateTime1);
        System.out.println(parse1);

        LocalTime time1 = LocalTime.of(12, 12, 22);
        LocalTime parse2 = LocalTime.parse("12:12:22");

    }
}
