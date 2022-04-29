package com.rota.commands.shift;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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
        
        String h_border = StringUtils.repeat("-", 35)+"\n";
        
        String filling = attributes.stream()
            .map(x-> {String temp = "|  "+x+" : " + valList.get(attributes.indexOf(x)).toString();
                        String padding = StringUtils.repeat(" ", 34-temp.length()) + "|";
                        String rtrn = temp+padding+"\n";
                        return rtrn;})
            .reduce("",String::concat);
        
        String resy = "\n\n\n\n\n"+h_border+ filling + h_border;
        return resy;
    }
}