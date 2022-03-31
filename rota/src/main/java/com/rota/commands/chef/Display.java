package com.rota.commands.chef;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.rota.entity.Chef;
public class Display {
    
    public static String display(Chef chef) {
        
        List<String> attributes = Arrays.asList("chef_Id","First Name", "Last Name",  "Hourly wage","Contact no");
        List<Object> valList = Arrays.asList(chef.getChef_id(),chef.getF_name(),chef.getL_name(),chef.getH_wage(),chef.getContact_no());
        
        String h_border = StringUtils.repeat("-", 35)+"\n";
        
        String filling = attributes.stream()
            .map(x-> {String temp = "|  "+x+" : " + valList.get(attributes.indexOf(x)).toString();
                        String padding = StringUtils.repeat(" ", 34-temp.length()) + "|";
                        String rtrn = temp+padding+"\n";
                        return rtrn;})
            .reduce("",String::concat);
        
        String resy = h_border+ filling + h_border;
        return resy;
    }
}