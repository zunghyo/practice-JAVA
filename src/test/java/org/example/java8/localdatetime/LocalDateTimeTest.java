package org.example.java8.localdatetime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

public class LocalDateTimeTest {
    
    @Test
    @DisplayName("레거시")
    void test0() throws InterruptedException {

        Date date = new Date();
        System.out.println("date = " + date);
        long time = date.getTime();
        System.out.println("time = " + time); //EPOCK 타임

        //mutable
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println("after3Seconds = " + after3Seconds);
        after3Seconds.setTime(time);
        System.out.println("after3Seconds = " + after3Seconds);

        //month에 버그 -> 0이 1월임
        Calendar calendar = new GregorianCalendar();
        Calendar birthDay = new GregorianCalendar(1992, 3, 19);
        System.out.println("birthDay = " + birthDay.getTime());

        SimpleFormatter simpleFormatter = new SimpleFormatter();
    }

    @Test
    @DisplayName("Instant test")
    void test1(){
        Instant instant = Instant.now();
        System.out.println(instant);    // 기준시 UTC, GMT
        System.out.println(instant.atZone(ZoneId.of("UTC")));
        System.out.println(instant.atZone(ZoneId.systemDefault()));
    }

   @Test
   @DisplayName("LocalDateTime")
   void test2(){
       //LocalDateTime 사용 -> 서버의 시간 기준
       LocalDateTime now = LocalDateTime.now();
       System.out.println(now);

       LocalDateTime birthDay = LocalDateTime.of(1992, Month.MARCH, 19, 0, 0, 0);
       System.out.println(birthDay);

       ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
       System.out.println(nowInKorea);
   }

   @Test
   @DisplayName("Period - 휴먼용 시간")
   void test3(){
       //LocalDate 사용
       LocalDate today = LocalDate.now();
       LocalDate thisYearBirthDay = LocalDate.of(2023, 03, 19);

       Period between = Period.between(today, thisYearBirthDay);
       System.out.println(between.getDays());

       Period until = today.until(thisYearBirthDay);
       System.out.println(until);
       System.out.println(until.get(ChronoUnit.DAYS));
   }

    @Test
    @DisplayName("Duration - 머신용 시간")
    void test4() {
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println(between.getSeconds());
    }

    @Test
    @DisplayName("파싱 또는 포매팅")
    void test5(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);

        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(formatter));

        LocalDate parse = LocalDate.parse("07/15/1982", formatter);
        System.out.println(parse);
    }

    @Test
    @DisplayName("레거시 API")
    void test6(){
        //Date -> Instant
        Date date = new Date();
        Instant instant = date.toInstant();
        System.out.println("Instant = " + instant);

        //Instant -> Date
        Date newDate = Date.from(instant);
        System.out.println("Date = " + newDate);

        //GregorianCalendar -> LocalDateTime
        GregorianCalendar calendar = new GregorianCalendar();
        LocalDateTime dateTime = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("dateTime = " + dateTime);

        //레거시 -> 최근
        ZoneId newZoneAPI = TimeZone.getTimeZone("PST").toZoneId();
        System.out.println("newZoneAPI = " + newZoneAPI);
        //최근 -> 레거시
        TimeZone legacyZoneAPI = TimeZone.getTimeZone(newZoneAPI);
        System.out.println("legacyZoneAPI = " + legacyZoneAPI);
    }

}
