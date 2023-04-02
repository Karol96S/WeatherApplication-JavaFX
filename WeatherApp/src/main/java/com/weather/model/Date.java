package com.weather.model;

import java.time.DayOfWeek;

public class Date {
    public static String translatedDayOfTheWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "Poniedziałek";
            case TUESDAY:
                return "Wtorek";
            case WEDNESDAY:
                return "Środa";
            case THURSDAY:
                return "Czwartek";
            case FRIDAY:
                return "Piątek";
            case SATURDAY:
                return "Sobota";
            case SUNDAY:
                return "Niedziela";
            default:
                return null;
        }
    }
}
