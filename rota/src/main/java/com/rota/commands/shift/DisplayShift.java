package com.rota.commands.shift;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import com.rota.entity.Shift;

public class DisplayShift {
    
    public static String display(Shift shift) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        
        List<String> attributes = Arrays.asList(
             "shift_id",
             "date    ", 
             "start   ", 
             "finish  ", 
             "break   ", 
             "chef    "
             );

        List<Object> valList = Arrays.asList(
            shift.getShift_id(),
            shift.getDateOf(),
            shift.getStartTime().format(fmt),
            shift.getEndTime().format(fmt),
            shift.getBreakDurationInHours(),
            shift.getChef().getF_name()+" "+shift.getChef().getL_name()
            );
        
        String h_border = StringUtils.repeat("-", 32)+"\n";
        
        String filling = attributes.stream()
            .map(x-> {String temp = "|  "+x+" : " + valList.get(attributes.indexOf(x)).toString();
                        String padding = StringUtils.repeat(" ", 31-temp.length()) + "|";
                        String rtrn = temp+padding+"\n";
                        return rtrn;})
            .reduce("",String::concat);
        
        String resy = h_border+ filling + h_border;
        return resy;
    }

    public static String[] createArrays(Shift shift) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        String[] resy = new String[8];
        List<String> attributes = Arrays.asList(
             "shift_id",
             "date    ", 
             "start   ", 
             "finish  ", 
             "break   ", 
             "chef    "
             );

        List<Object> valList = Arrays.asList(
            shift.getShift_id(),
            shift.getDateOf(),
            shift.getStartTime().format(fmt),
            shift.getEndTime().format(fmt),
            shift.getBreakDurationInHours(),
            shift.getChef().getF_name()+" "+shift.getChef().getL_name()
            );
        
        String h_border = StringUtils.repeat("-", 32);
        
        IntStream.range(1, 7)
            .boxed()
            .forEach(
                i -> {String temp = "| "+attributes.get(i-1)+" : " + valList.get(i-1).toString();
                      String padding = StringUtils.repeat(" ", 31-temp.length()) + "|";
                      String rtrn = temp+padding;
                      resy[i] = rtrn;}
            );
        resy[0] = h_border;
        resy[7] = h_border;
        return resy;
    }

    static List<String> shiftsToLines(List<String[]> shifts) {
        List<String> lines = new ArrayList<>();
        IntStream.range(0, 8)
            .boxed()
            .map(i -> {
                String line = shifts.stream()
                                   .map(b -> b[i])
                                   .reduce("", String::concat);
                return line;
            })
            .forEach(lines::add);
        return lines;
    }
}