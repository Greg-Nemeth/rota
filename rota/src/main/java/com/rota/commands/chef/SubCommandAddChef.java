package com.rota.commands.chef;

import com.rota.bean.Chef;
import com.rota.repository.ChefRepository;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine.Command;

@Command(name = "add",
  description = "add the details of a chef to the system",
  mixinStandardHelpOptions = true)
public class SubCommandAddChef implements Runnable {

    ChefRepository chefRepository;
    Chef chef;
    
    @Inject
    public SubCommandAddChef(ChefRepository chefRepository, Chef chef) {
        this.chefRepository = chefRepository;
        this.chef = chef;
    }

    
    public String display(Chef chef) {
        
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
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter chef's first name: ");
        chef.setF_name(sc.next());
        System.out.println("Enter chef's last name:");
        chef.setL_name(sc.next());
        System.out.println("Enter chef's phone number in the format '+447321583291'");
        chef.setContact_no(sc.next());
        System.out.println("Enter chef's hourly wage :");
        chef.setH_wage(sc.nextFloat());
        Chef c1 = chefRepository.save(chef);
        display(chef);
    }
    
}