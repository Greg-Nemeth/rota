package com.rota.commands.shift;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rota.entity.Chef;
import com.rota.entity.Shift;

public class DisplayShift {
    
    public static String display(Shift shift, Chef chef) {
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
            shift.getDate_of(),
            shift.getStart_time().format(fmt),
            shift.getEnd_time().format(fmt),
            shift.getBreak_duration_h(),
            chef.getF_name()+" "+chef.getL_name()
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