package org.appDesktop.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateService {
    public static LocalDate getFirstDayOfTheWeek() {
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    public static LocalDate getLastDayOfTheWeek() {
        return getFirstDayOfTheWeek().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    public static LocalDate getLast28Day() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusDays(28);
    }

    public static LocalDate getTodayDate() {
        return LocalDate.now();
    }
}
