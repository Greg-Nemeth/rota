package com.rota.commands.shift;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import com.rota.entity.Chef;
import com.rota.entity.Shift;


public class DisplayWeek {
    
    public static List<String> displayWeeklyRota(List<Shift> shifts, List<LocalDate> week) {
        DateTimeFormatter fmtDay = DateTimeFormatter.ofPattern("eeee");
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd MMM uuuu");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm");
        

        Collections.sort(week);

        List<String> datesAsString = week.stream()
            .map(x -> x.format(fmtDate))
            .collect(Collectors.toList());
        List<String> daysList = week.stream()
            .map(y -> y.format(fmtDay))
            .collect(Collectors.toList());

        String empty = " ";

        Map<Chef, List<Shift>> shiftsByChef = shifts.stream()
            .collect(Collectors.groupingBy(
                Shift::getChef
                ));

        List<String[]> dayBoxes = new ArrayList<>(8);
        dayBoxes.add(0, box(empty));
        IntStream.range(1, 8)
            .boxed()
            .forEach(
                i -> dayBoxes.add(i ,box(daysList.get(i-1), datesAsString.get(i-1))) 
            );
        List<String> lines = boxesToLines(dayBoxes);

        shiftsByChef.entrySet().forEach(e -> {
            List<String[]> chefBox = new ArrayList<>(8);
            chefBox.add(0, box(e.getKey().getF_name(), e.getKey().getL_name()));
            IntStream.range(1, 8)
                .boxed()
                .forEach(i -> {
                    LocalDate targetDate = week.get(i-1);
                    e.getValue().stream()
                        .filter(s -> s.getDateOf().equals(targetDate))
                        .findFirst()
                        .ifPresentOrElse(
                            (value) -> {String timeToString = value.getStartTime().format(fmtTime)
                                                     + " - "+ value.getEndTime().format(fmtTime);
                                        chefBox.add(i, box(timeToString));},
                            () -> chefBox.add(i, box("off"))
                            );
    
                });
            lines.addAll(boxesToLines(chefBox));
        });
        

        return lines;
    }

    static List<String> boxesToLines(List<String[]> boxes) {
        List<String> lines = new ArrayList<>();
        IntStream.range(0, 6)
            .boxed()
            .map(i -> {
                String line = boxes.stream()
                                   .map(b -> b[i])
                                   .reduce("", String::concat);
                return line;
            })
            .forEach(lines::add);
        return lines;
    }

    static String[] box(String toBeBoxed) {
        String eighteenSpacesPlusRightWall = StringUtils.repeat(" ", 18)+"|";
        String top = StringUtils.repeat("_", 20);
        String emptyLine = "|"+ eighteenSpacesPlusRightWall;
        String bottom = top.replace('_', '-');
        
        int inputLength = toBeBoxed.length();
        int halfLengthRoundedUp = inputLength + (inputLength & 1) >> 1;

        String leftPadding = StringUtils.repeat(" ", 10 - halfLengthRoundedUp);
        String rightPadding = StringUtils.repeat(" ", 18 - inputLength - leftPadding.length());

        String content = "|" + leftPadding + toBeBoxed + rightPadding + "|";

        String[] stringBox = {top,
                        emptyLine,
                        content,
                        emptyLine,
                        emptyLine,
                        bottom};

        return stringBox;
    }

    static String[] box(String toBeBoxed1, String tobeBoxed2) {
        String eighteenSpacesPlusRightWall = StringUtils.repeat(" ", 18)+"|";
        String top = StringUtils.repeat("_", 20);
        String emptyLine = "|"+ eighteenSpacesPlusRightWall;
        String bottom = top.replace('_', '-');
        
        List<String> inputList = List.of(toBeBoxed1, tobeBoxed2);

        List<String> contentList = inputList.stream()
            .map(input -> {
                int inputLength = input.length();
                int halfLengthRoundedUp = inputLength + (inputLength & 1) >> 1;

                String leftPadding = StringUtils.repeat(" ", 10 - halfLengthRoundedUp);
                String rightPadding = StringUtils.repeat(" ", 18 - inputLength - leftPadding.length());

                String content = "|" + leftPadding + input + rightPadding + "|";
                return content;
                        })
            .collect(Collectors.toList());

        String[] stringBox = {top,
                        emptyLine,
                        contentList.get(0),
                        contentList.get(1),
                        emptyLine,
                        bottom};

        return stringBox;
    }


}