package com.rota.commands.chef;


import java.util.Scanner;

import com.rota.entity.Chef;
import com.rota.repository.ChefRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;

@Singleton
@Command(name = "add",
  description = "add the details of a chef to the system",
  mixinStandardHelpOptions = true)
public class SubCommandAddChef implements Runnable {

    ChefRepository chefRepository;
    
    
    
    public SubCommandAddChef(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
        
    }

    

  
    @Override
    public void run() {
        Chef chef = new Chef();
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
        System.out.println(DisplayChef.display(c1));
    }
    
}