package com.rota.commands.chef;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import com.rota.entity.Chef;
import com.rota.repository.ChefRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Singleton
@Command(name = "update",
  description = "update chefs' details",
  mixinStandardHelpOptions = true)
public class SubCommandUpdateChef implements Runnable{


    @Parameters(paramLabel="specify id of chef to be updated")
    Long chef_id;

    ChefRepository chefRepository;

    public SubCommandUpdateChef(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }
    @Transactional
    @Override
    public void run() {
        Chef chef = chefRepository.findById(chef_id).get();
        Scanner sc = new Scanner(System.in);
        System.out.println(DisplayChef.display(chef));
        List<String> attributes = Arrays.asList("1: First Name", "2: Last Name",  "3: Hourly wage","4: Contact no");
        System.out.println("Which detail would you like to update? use the numbers to select an option!\n");
        attributes.forEach(System.out::println);
        System.out.println("select option:");
        Integer opt = Integer.valueOf(sc.next());
        
        switch (opt) {
            case 1 -> { System.out.println("\nPlease enter new first name: ");
                        String firstName = sc.next();
                        chef.setF_name(firstName);
                        chefRepository.update(chef);
                        System.out.println(DisplayChef.display(chef)); 
            }
            case 2 -> { System.out.println("\nPlease enter new last name: ");
                        String lastName = sc.next();
                        chef.setL_name(lastName);
                        chefRepository.update(chef);
                        System.out.println(DisplayChef.display(chef));   
            }
            case 3 -> { System.out.println("\nPlease enter new hourly wage: ");
                        float wage = sc.nextFloat();
                        chef.setH_wage(wage);
                        chefRepository.update(chef);
                        System.out.println(DisplayChef.display(chef)); 
            }
            case 4 -> { System.out.println("\nPlease enter new phone number in the form +44xxxxxxxxxxxx: ");
                        String phone = sc.next();
                        chef.setContact_no(phone);
                        chefRepository.update(chef);
                        System.out.println(DisplayChef.display(chef));  
            }
            default -> System.out.println("Please try again and select a valid option");
        }
        

    }
}