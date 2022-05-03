package com.rota.commands.shift;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Singleton;

@Singleton
public class ShowDate {

    private final LocalDate todayDate;
    private final List<LocalDate> daysOfThisWeek;
    private final List<LocalDate> daysOfNextWeek;
    private final LocalDate nextMonday;
    private final LocalDate thisMonday;

    public ShowDate() {
        daysOfThisWeek = new ArrayList<>();
        daysOfNextWeek = new ArrayList<>();
        todayDate = LocalDate.now();
        int xthDayOfWeek = todayDate.getDayOfWeek().getValue();
        nextMonday = todayDate.plusDays(8-xthDayOfWeek);
        thisMonday = nextMonday.minusWeeks(1);
        for (int i = 0; i < 7; i++) {
            LocalDate tempDayOfWeek = thisMonday.plusDays(i);
            daysOfThisWeek.add(tempDayOfWeek);
        }
        daysOfThisWeek.stream()
                .map(x -> x.plusDays(7))
                .forEach(daysOfNextWeek::add);
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public List<LocalDate> getDaysOfThisWeek() {
        return daysOfThisWeek;
    }

    public List<LocalDate> getDaysOfNextWeek() {
        return daysOfNextWeek;
    }

    public LocalDate getNextMonday() {
        return nextMonday;
    }

    public LocalDate getThisMonday() {
        return thisMonday;
    }

    
}