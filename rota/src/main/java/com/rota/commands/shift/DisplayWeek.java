package com.rota.commands.shift;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.rota.entity.Chef;
import com.rota.entity.Shift;


public class DisplayWeek {

    public static String displayWeeklyRota(List<LocalDate> givenByShowDate, List<Chef> chefs, List<Shift> shifts) {
        DateTimeFormatter fmtDay = DateTimeFormatter.ofPattern("eeee");
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd MMM uuuu");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm");
        List<String> daysAsStrings = givenByShowDate.stream()
                                                    .map(x -> x.format(fmtDay))
                                                    .toList();

        List<String> datesAsStrings = givenByShowDate.stream()
                                                    .map(y -> y.format(fmtDate))
                                                    .toList();

        String nineteenSpacesPlusRightWall = StringUtils.repeat(" ", 19)+"|";
        String top = StringUtils.repeat("_", 160);
        // String emptyLineWithDeviders = "|"+StringUtils.repeat(nineteenSpacesPlusRightWall, 8);
        // String bottom = top.replace('_', '-');

        List<List<String>> matrixRota = new ArrayList<>();
        matrixRota.add(datesAsStrings);
        matrixRota.add(daysAsStrings);
        
        List<List<String>> chefRows = chefs.stream()
                                           .map(c -> c.getF_name())
                                           .map(n -> {List<String> indRow = new ArrayList<>(8);
                                                      indRow.add(0, n);
                                                      return indRow;})
                                           .collect(Collectors.toList());

        matrixRota.addAll(chefRows);

        for (int i=1; i<matrixRota.size(); i++) {
            for (int j=1; j<8;j++) {
                Long thisChef = chefs.get(i-1).getChef_id();
                LocalDate thisDate = givenByShowDate.get(j-1);
                String filling = shifts.stream()
                                                 .filter(a -> a.getChef().getChef_id().equals(thisChef))
                                                 .filter(b -> b.getDateOf().equals(thisDate))
                                                 .map(t -> {String result = t.getStart_time().format(fmtTime)+ " - " +t.getEnd_time().format(fmtTime);
                                                            return result;})
                                                 .toString();
                matrixRota.get(i).add(j, filling);
            }
        }

        

        return matrixRota.toString();

    }
}